package pl.put.poznan.Sorting.logic;

import java.util.List;

public interface SortingAlgorithm {

    String getName(); // Name of the algorythm

    /**
     * Sortuje listę liczb całkowitych.
     *
     * @param data  dane wejściowe (nie modyfikować oryginału)
     * @param limit limit liczby kroków; jeśli <= 0, sortuje do końca
     * @return nowa lista, (częściowo) posortowana
     */
    List<Integer> sort(List<Integer> data, int limit);
}
