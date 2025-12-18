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
    public List<Integer> sort(List<Integer> data, int limit) {
        List<Integer> list = copyOf(data);
        int[] stepsLeft = new int[]{ normalizeLimit(limit) };
        quickSort(list, 0, list.size() - 1, stepsLeft);
        return list;
    }

    private void quickSort(List<Integer> list, int low, int high, int[] stepsLeft) {
        if (low < high && stepsLeft[0] > 0) {
            int pi = partition(list, low, high, stepsLeft);
            quickSort(list, low, pi - 1, stepsLeft);
            quickSort(list, pi + 1, high, stepsLeft);
        }
    }

    private int partition(List<Integer> list, int low, int high, int[] stepsLeft) {
        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high && stepsLeft[0] > 0; j++) {
            stepsLeft[0]--;
            if (list.get(j) <= pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}