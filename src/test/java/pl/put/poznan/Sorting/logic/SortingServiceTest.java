package pl.put.poznan.Sorting.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SortingServiceTest {

    @Mock
    private SortingAlgorithm mockAlgorithm;

    @Test
    void testSortWithMockAlgorithm() {
        List<List<Object>> inputData = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(1),
                Arrays.asList(2)
        );

        List<List<Object>> expectedResult = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );

        when(mockAlgorithm.getName()).thenReturn("mock-sort");
        when(mockAlgorithm.sort(anyList(), anyInt(), anyInt(), anyBoolean()))
                .thenReturn(expectedResult);

        assertEquals("mock-sort", mockAlgorithm.getName());

        List<List<Object>> result = mockAlgorithm.sort(inputData, 0, 0, false);

        assertEquals(expectedResult, result);
        assertEquals(1, result.get(0).get(0));

        verify(mockAlgorithm, times(1)).sort(inputData, 0, 0, false);
        verify(mockAlgorithm, atLeastOnce()).getName();
    }

    @Test
    void testSortWithParametersMock() {
        when(mockAlgorithm.sort(
                eq(Arrays.asList(Arrays.asList(5), Arrays.asList(1))),
                eq(0),
                eq(10),
                eq(true)
        )).thenReturn(Arrays.asList(Arrays.asList(5), Arrays.asList(1)));

        List<List<Object>> data = Arrays.asList(
                Arrays.asList(5),
                Arrays.asList(1)
        );

        List<List<Object>> result = mockAlgorithm.sort(data, 0, 10, true);

        assertNotNull(result);
        verify(mockAlgorithm).sort(data, 0, 10, true);
    }
}