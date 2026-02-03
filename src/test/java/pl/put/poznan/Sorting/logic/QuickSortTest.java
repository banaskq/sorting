package pl.put.poznan.Sorting.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    private QuickSort quickSort;

    @BeforeEach
    void setUp() {
        quickSort = new QuickSort();
    }

    @Test
    void testGetName() {
        assertEquals("quick", quickSort.getName());
    }

    @Test
    void testSortAscending() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(10),
                Arrays.asList(7),
                Arrays.asList(8),
                Arrays.asList(9),
                Arrays.asList(1),
                Arrays.asList(5)
        );

        List<List<Object>> result = quickSort.sort(data, 0, 0, false);

        assertEquals(1, result.get(0).get(0));
        assertEquals(5, result.get(1).get(0));
        assertEquals(7, result.get(2).get(0));
        assertEquals(8, result.get(3).get(0));
        assertEquals(9, result.get(4).get(0));
        assertEquals(10, result.get(5).get(0));
    }

    @Test
    void testSortDescending() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(6),
                Arrays.asList(1)
        );

        List<List<Object>> result = quickSort.sort(data, 0, 0, true);

        assertEquals(6, result.get(0).get(0));
        assertEquals(3, result.get(1).get(0));
        assertEquals(1, result.get(2).get(0));
    }

    @Test
    void testSortWithLimit() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(5),
                Arrays.asList(1),
                Arrays.asList(4),
                Arrays.asList(2),
                Arrays.asList(3)
        );

        List<List<Object>> result = quickSort.sort(data, 0, 5, false);

        assertNotNull(result);
    }

    @Test
    void testSingleElement() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(42)
        );

        List<List<Object>> result = quickSort.sort(data, 0, 0, false);

        assertEquals(1, result.size());
        assertEquals(42, result.get(0).get(0));
    }

    @Test
    void testAlreadySorted() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4)
        );

        List<List<Object>> result = quickSort.sort(data, 0, 0, false);

        assertEquals(1, result.get(0).get(0));
        assertEquals(4, result.get(3).get(0));
    }
}