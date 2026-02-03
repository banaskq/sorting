package pl.put.poznan.Sorting.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    private BubbleSort bubbleSort;

    @BeforeEach
    void setUp() {
        bubbleSort = new BubbleSort();
    }

    @Test
    void testGetName() {
        assertEquals("bubble", bubbleSort.getName());
    }

    @Test
    void testSortAscending() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(5),
                Arrays.asList(3),
                Arrays.asList(8),
                Arrays.asList(1),
                Arrays.asList(2)
        );

        List<List<Object>> result = bubbleSort.sort(data, 0, 0, false);

        assertEquals(1, result.get(0).get(0));
        assertEquals(2, result.get(1).get(0));
        assertEquals(3, result.get(2).get(0));
        assertEquals(5, result.get(3).get(0));
        assertEquals(8, result.get(4).get(0));
    }

    @Test
    void testSortDescending() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(5),
                Arrays.asList(3)
        );

        List<List<Object>> result = bubbleSort.sort(data, 0, 0, true);

        assertEquals(5, result.get(0).get(0));
        assertEquals(3, result.get(1).get(0));
        assertEquals(1, result.get(2).get(0));
    }

    @Test
    void testSortWithLimit() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(5),
                Arrays.asList(3),
                Arrays.asList(8),
                Arrays.asList(1)
        );

        List<List<Object>> result = bubbleSort.sort(data, 0, 3, false);

        assertNotEquals(1, result.get(0).get(0));
    }

    @Test
    void testSortMultiColumn() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList("Jan", 25),
                Arrays.asList("Anna", 30),
                Arrays.asList("Piotr", 20)
        );

        List<List<Object>> result = bubbleSort.sort(data, 1, 0, false);

        assertEquals("Piotr", result.get(0).get(0));
        assertEquals("Jan", result.get(1).get(0));
        assertEquals("Anna", result.get(2).get(0));
    }

    @Test
    void testEmptyData() {
        List<List<Object>> data = Arrays.asList();

        List<List<Object>> result = bubbleSort.sort(data, 0, 0, false);

        assertTrue(result.isEmpty());
    }
}