package pl.put.poznan.Sorting.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OtherAlgorithmsTest {

    static Stream<SortingAlgorithm> algorithmProvider() {
        return Stream.of(
                new SelectionSort(),
                new InsertionSort(),
                new MergeSort(),
                new HeapSort()
        );
    }

    @ParameterizedTest
    @MethodSource("algorithmProvider")
    void testAlgorithmNameNotNull(SortingAlgorithm algorithm) {
        assertNotNull(algorithm.getName());
        assertFalse(algorithm.getName().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("algorithmProvider")
    void testSortAscending(SortingAlgorithm algorithm) {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(5),
                Arrays.asList(2),
                Arrays.asList(8),
                Arrays.asList(1),
                Arrays.asList(3)
        );

        List<List<Object>> result = algorithm.sort(data, 0, 0, false);

        // Sprawdź czy posortowane rosnąco
        for (int i = 0; i < result.size() - 1; i++) {
            int current = (int) result.get(i).get(0);
            int next = (int) result.get(i + 1).get(0);
            assertTrue(current <= next);
        }
    }

    @ParameterizedTest
    @MethodSource("algorithmProvider")
    void testSortDescending(SortingAlgorithm algorithm) {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(5),
                Arrays.asList(3),
                Arrays.asList(2),
                Arrays.asList(4)
        );

        List<List<Object>> result = algorithm.sort(data, 0, 0, true);

        // Sprawdź czy posortowane malejąco
        for (int i = 0; i < result.size() - 1; i++) {
            int current = (int) result.get(i).get(0);
            int next = (int) result.get(i + 1).get(0);
            assertTrue(current >= next);
        }
    }

    @ParameterizedTest
    @MethodSource("algorithmProvider")
    void testSortWithLimit(SortingAlgorithm algorithm) {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(9),
                Arrays.asList(3),
                Arrays.asList(7),
                Arrays.asList(1),
                Arrays.asList(5)
        );

        List<List<Object>> result = algorithm.sort(data, 0, 10, false);

        assertNotNull(result);
        assertEquals(5, result.size());
    }

    @ParameterizedTest
    @MethodSource("algorithmProvider")
    void testSortEmptyData(SortingAlgorithm algorithm) {
        List<List<Object>> data = Arrays.asList();

        List<List<Object>> result = algorithm.sort(data, 0, 0, false);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSelectionSortSpecific() {
        SelectionSort selectionSort = new SelectionSort();

        List<List<Object>> data = Arrays.asList(
                Arrays.asList("Z"),
                Arrays.asList("A"),
                Arrays.asList("M"),
                Arrays.asList("K")
        );

        List<List<Object>> result = selectionSort.sort(data, 0, 0, false);

        assertEquals("A", result.get(0).get(0));
        assertEquals("K", result.get(1).get(0));
        assertEquals("M", result.get(2).get(0));
        assertEquals("Z", result.get(3).get(0));
    }

    @Test
    void testInsertionSortSpecific() {
        InsertionSort insertionSort = new InsertionSort();

        List<List<Object>> data = Arrays.asList(
                Arrays.asList(9.5),
                Arrays.asList(7.2),
                Arrays.asList(8.1),
                Arrays.asList(6.8)
        );

        List<List<Object>> result = insertionSort.sort(data, 0, 0, true);

        assertTrue((Double) result.get(0).get(0) > (Double) result.get(1).get(0));
    }

    @Test
    void testMergeSortSpecific() {
        MergeSort mergeSort = new MergeSort();

        List<List<Object>> data = Arrays.asList(
                Arrays.asList(100),
                Arrays.asList(10),
                Arrays.asList(50),
                Arrays.asList(30),
                Arrays.asList(80)
        );

        List<List<Object>> result = mergeSort.sort(data, 0, 0, false);

        assertEquals(10, result.get(0).get(0));
        assertEquals(100, result.get(4).get(0));
    }

    @Test
    void testHeapSortSpecific() {
        HeapSort heapSort = new HeapSort();

        List<List<Object>> data = Arrays.asList(
                Arrays.asList(15),
                Arrays.asList(3),
                Arrays.asList(9),
                Arrays.asList(8),
                Arrays.asList(10),
                Arrays.asList(2),
                Arrays.asList(4)
        );

        List<List<Object>> result = heapSort.sort(data, 0, 0, false);

        assertEquals(2, result.get(0).get(0));
        assertEquals(15, result.get(6).get(0));
    }
}