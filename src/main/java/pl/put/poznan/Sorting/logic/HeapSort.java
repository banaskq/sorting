package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeapSort extends AbstractLimitedSortingAlgorithm {

    @Override
    public String getName() {
        return "heap";
    }

    @Override
    public List<Integer> sort(List<Integer> data, int limit) {
        List<Integer> list = copyOf(data);
        int n = list.size();
        int stepsLeft = normalizeLimit(limit);

        // Heap building
        for (int i = n / 2 - 1; i >= 0 && stepsLeft > 0; i--) {
            stepsLeft = heapify(list, n, i, stepsLeft);
        }

        // Getting elements from a heap
        for (int i = n - 1; i >= 0 && stepsLeft > 0; i--) {
            int tmp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, tmp);
            stepsLeft = heapify(list, i, 0, stepsLeft);
        }

        return list;
    }

    private int heapify(List<Integer> list, int n, int i, int stepsLeft) {
        if (stepsLeft <= 0) return 0;

        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && stepsLeft > 0) {
            stepsLeft--;
            if (list.get(l) > list.get(largest)) {
                largest = l;
            }
        }

        if (r < n && stepsLeft > 0) {
            stepsLeft--;
            if (list.get(r) > list.get(largest)) {
                largest = r;
            }
        }

        if (largest != i && stepsLeft > 0) {
            int swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);
            stepsLeft = heapify(list, n, largest, stepsLeft);
        }
        return stepsLeft;
    }
}