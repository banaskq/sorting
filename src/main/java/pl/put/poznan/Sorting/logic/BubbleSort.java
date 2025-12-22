package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementacja algorytmu Bubble Sort z obsługą:
 * <ul>
 *     <li>sortowania rosnącego i malejącego,</li>
 *     <li>limitowania liczby kroków sortowania,</li>
 *     <li>sortowania danych liczbowych oraz tablic obiektów.</li>
 * </ul>
 *
 * <p>Bubble Sort działa poprzez wielokrotne porównywanie sąsiednich elementów
 * i zamienianie ich miejscami, jeśli znajdują się w niewłaściwej kolejności.
 * Proces powtarza się aż do pełnego posortowania danych lub wyczerpania limitu kroków.</p>
 *
 * <p>Algorytm operuje na danych w postaci tablicy dwuwymiarowej
 * (lista wierszy), co umożliwia sortowanie zarówno listy liczb
 * (przekształconej do formatu [[x], [y], ...]), jak i tablic obiektów.</p>
 */
@Component
public class BubbleSort extends AbstractLimitedSortingAlgorithm {

    /**
     * Zwraca nazwę algorytmu sortowania.
     *
     * @return nazwa algorytmu: "bubble"
     */
    @Override
    public String getName() {
        return "bubble";
    }

    /**
     * Sortuje dane metodą Bubble Sort.
     *
     * <p>Algorytm wykonuje maksymalnie tyle kroków, ile określa parametr {@code limit}.
     * Każde porównanie lub zamiana elementów zmniejsza liczbę pozostałych kroków.
     * Jeśli limit zostanie wyczerpany, algorytm zwraca częściowo posortowaną listę.</p>
     *
     * @param data        dane wejściowe jako lista wierszy
     * @param sortByIndex indeks kolumny, po której odbywa się sortowanie
     * @param limit       maksymalna liczba kroków sortowania; wartości ≤ 0 oznaczają brak limitu
     * @param descending  kierunek sortowania: {@code true} – malejąco, {@code false} – rosnąco
     * @return posortowane lub częściowo posortowane dane
     */
    @Override
    public List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending) {
        List<List<Object>> list = copyOf(data);
        int n = list.size();
        int stepsLeft = normalizeLimit(limit);

        boolean swapped;
        for (int i = 0; i < n - 1 && stepsLeft > 0; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1 && stepsLeft > 0; j++) {
                stepsLeft--;

                if (compareRows(list.get(j), list.get(j + 1), sortByIndex, descending) > 0) {
                    List<Object> tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return list;
    }
}