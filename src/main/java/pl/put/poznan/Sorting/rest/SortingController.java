package pl.put.poznan.Sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.Sorting.logic.SortingAlgorithm;

import java.util.*;

@RestController
@RequestMapping("/api/sort")
public class SortingController {

    private final Map<String, SortingAlgorithm> algorithmsByName;

    public SortingController(List<SortingAlgorithm> algorithms) {
        // @Component SortingAlgorithm
        this.algorithmsByName = new HashMap<>();
        for (SortingAlgorithm alg : algorithms) {
            algorithmsByName.put(alg.getName().toLowerCase(Locale.ROOT), alg);
        }
    }

    @PostMapping
    public SortingResponse sort(@RequestBody SortingRequest request) {

        // Data validation:
        if (request.getData() == null || request.getData().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Zbiór danych jest pusty."
            );
        }

        List<String> algNames = request.getAlgorithms();
        if (algNames == null || algNames.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nie wybrano żadnego algorytmu sortującego (wymagane 1..6)."
            );
        }

        if (algNames.size() < 1 || algNames.size() > 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nie wybrano odpowiedniej liczby algorytmów sortujących (dozwolone 1..6)."
            );
        }

        // Algorithm name check
        List<SortingResult> results = new ArrayList<>();
        int limit = (request.getLimit() != null) ? request.getLimit() : 0;

        for (String name : algNames) {
            String key = name.toLowerCase(Locale.ROOT);
            SortingAlgorithm algorithm = algorithmsByName.get(key);
            if (algorithm == null) {
                // "Incorrect column names" are interpreted here as incorrect algorithm names
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Unidentified algorithm name: " + name
                );
            }

            long start = System.nanoTime();
            List<Integer> sorted = algorithm.sort(request.getData(), limit);
            long end = System.nanoTime();
            long durationMillis = (end - start) / 1_000_000;

            results.add(new SortingResult(algorithm.getName(), durationMillis, sorted));
        }

        return new SortingResponse(results);
    }
}