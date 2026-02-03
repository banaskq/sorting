package pl.put.poznan.Sorting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.put.poznan.Sorting.logic.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SortingIntegrationTest {

    @Autowired(required = false)
    private BubbleSort bubbleSort;

    @Autowired(required = false)
    private QuickSort quickSort;

    @Autowired(required = false)
    private SelectionSort selectionSort;

    @Test
    void testSpringContextLoads() {
        assertNotNull(bubbleSort);
        assertNotNull(quickSort);
        assertNotNull(selectionSort);
    }

    @Test
    void testAllAlgorithmsProduceSameResult() {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList(7),
                Arrays.asList(2),
                Arrays.asList(9),
                Arrays.asList(1),
                Arrays.asList(5)
        );

        List<List<Object>> bubbleResult = bubbleSort.sort(data, 0, 0, false);
        List<List<Object>> quickResult = quickSort.sort(data, 0, 0, false);
        List<List<Object>> selectionResult = selectionSort.sort(data, 0, 0, false);

        for (int i = 0; i < data.size(); i++) {
            assertEquals(bubbleResult.get(i).get(0), quickResult.get(i).get(0));
            assertEquals(quickResult.get(i).get(0), selectionResult.get(i).get(0));
        }
    }
}