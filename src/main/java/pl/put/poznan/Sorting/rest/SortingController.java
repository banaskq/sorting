package pl.put.poznan.Sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.Sorting.logic.SortingAlgorithm;

import java.util.*;

/**
 * Kontroler REST odpowiedzialny za obsługę żądań sortowania.
 * Przyjmuje dane wejściowe w formacie JSON, waliduje je, rozpoznaje typ danych
 * (lista liczb lub tablica obiektów), a następnie uruchamia wybrane algorytmy sortowania.
 *
 * <p>Kontroler umożliwia:
 * <ul>
 *     <li>sortowanie wielu algorytmów jednocześnie,</li>
 *     <li>sortowanie rosnące lub malejące,</li>
 *     <li>sortowanie z limitem kroków,</li>
 *     <li>sortowanie listy liczb oraz tablicy obiektów,</li>
 *     <li>zwracanie czasu wykonania każdego algorytmu.</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping("/api/sort")
public class SortingController {

    private final Map<String, SortingAlgorithm> algorithmsByName;

    /**
     * Tworzy kontroler i rejestruje dostępne algorytmy sortowania.
     *
     * @param algorithms lista komponentów implementujących {@link SortingAlgorithm}
     */
    public SortingController(List<SortingAlgorithm> algorithms) {
        this.algorithmsByName = new HashMap<>();
        for (SortingAlgorithm alg : algorithms) {
            algorithmsByName.put(alg.getName().toLowerCase(Locale.ROOT), alg);
        }
    }

    /**
     * Główna metoda obsługująca żądanie sortowania.
     * Waliduje dane wejściowe, rozpoznaje typ danych, uruchamia wybrane algorytmy
     * i zwraca wyniki wraz z czasem wykonania.
     *
     * @param request obiekt zawierający dane wejściowe i konfigurację sortowania
     * @return obiekt zawierający wyniki działania algorytmów
     */
    @PostMapping
    public SortingResponse sort(@RequestBody SortingRequest request) {

        if (request.getData() == null || request.getData().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zbiór danych jest pusty.");
        }

        List<String> algNames = request.getAlgorithms();
        if (algNames == null || algNames.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nie wybrano żadnego algorytmu sortującego.");
        }

        boolean descending = "desc".equalsIgnoreCase(
                request.getOrder() != null ? request.getOrder() : "asc"
        );

        int limit = (request.getLimit() != null) ? request.getLimit() : 0;

        List<Object> rawData = request.getData();
        Object first = rawData.get(0);

        boolean isNumberList = first instanceof Number;
        List<List<Object>> table = new ArrayList<>();
        int sortByIndex;

        if (isNumberList) {
            for (Object obj : rawData) {
                List<Object> row = new ArrayList<>();
                row.add(obj);
                table.add(row);
            }
            sortByIndex = 0;
        } else if (first instanceof List) {
            for (Object rowObj : rawData) {
                @SuppressWarnings("unchecked")
                List<Object> row = new ArrayList<>((List<Object>) rowObj);
                table.add(row);
            }

            if (request.getSortByIndex() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Nie podano indeksu kolumny sortowania.");
            }

            sortByIndex = request.getSortByIndex();
            if (table.get(0).size() <= sortByIndex || sortByIndex < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Niepoprawny indeks kolumny sortowania.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nieobsługiwany format danych wejściowych.");
        }

        List<SortingResult> results = new ArrayList<>();

        for (String name : algNames) {
            SortingAlgorithm algorithm = algorithmsByName.get(name.toLowerCase(Locale.ROOT));
            if (algorithm == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Nieznany algorytm sortujący: " + name);
            }

            long start = System.nanoTime();
            List<List<Object>> sortedTable = algorithm.sort(table, sortByIndex, limit, descending);
            long end = System.nanoTime();

            Object outputData;
            if (isNumberList) {
                List<Object> flat = new ArrayList<>();
                for (List<Object> row : sortedTable) {
                    flat.add(row.get(0));
                }
                outputData = flat;
            } else {
                outputData = sortedTable;
            }

            results.add(new SortingResult(
                    algorithm.getName(),
                    (end - start) / 1_000_000,
                    outputData
            ));
        }

        return new SortingResponse(results);
    }
}