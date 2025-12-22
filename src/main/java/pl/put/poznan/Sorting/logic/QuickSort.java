package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuickSort extends AbstractLimitedSortingAlgorithm {

    @Override
    public String getName() {
        return "quick";
    }

    @Override
    public List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending) {
        List<List<Object>> list = copyOf(data);
        int[] stepsLeft = new int[]{ normalizeLimit(limit) };
        quickSort(list, 0, list.size() - 1, sortByIndex, stepsLeft, descending);
        return list;
    }

    private void quickSort(List<List<Object>> list, int low, int high,
                           int sortByIndex, int[] stepsLeft, boolean descending) {

        if (low < high && stepsLeft[0] > 0) {
            int pi = partition(list, low, high, sortByIndex, stepsLeft, descending);
            quickSort(list, low, pi - 1, sortByIndex, stepsLeft, descending);
            quickSort(list, pi + 1, high, sortByIndex, stepsLeft, descending);
        }
    }

    private int partition(List<List<Object>> list, int low, int high,
                          int sortByIndex, int[] stepsLeft, boolean descending) {

        List<Object> pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high && stepsLeft[0] > 0; j++) {
            stepsLeft[0]--;
            if (compareRows(list.get(j), pivot, sortByIndex, descending) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<List<Object>> list, int i, int j) {
        List<Object> tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}