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
    public List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending) {
        List<List<Object>> list = copyOf(data);
        int n = list.size();
        int stepsLeft = normalizeLimit(limit);

        for (int i = n / 2 - 1; i >= 0 && stepsLeft > 0; i--) {
            stepsLeft = heapify(list, n, i, sortByIndex, stepsLeft, descending);
        }

        for (int i = n - 1; i >= 0 && stepsLeft > 0; i--) {
            List<Object> tmp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, tmp);

            stepsLeft = heapify(list, i, 0, sortByIndex, stepsLeft, descending);
        }

        return list;
    }

    private int heapify(List<List<Object>> list, int n, int i,
                        int sortByIndex, int stepsLeft, boolean descending) {

        if (stepsLeft <= 0) return 0;

        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && stepsLeft > 0) {
            stepsLeft--;
            if (compareRows(list.get(l), list.get(largest), sortByIndex, descending) > 0) {
                largest = l;
            }
        }

        if (r < n && stepsLeft > 0) {
            stepsLeft--;
            if (compareRows(list.get(r), list.get(largest), sortByIndex, descending) > 0) {
                largest = r;
            }
        }

        if (largest != i && stepsLeft > 0) {
            List<Object> swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);

            stepsLeft = heapify(list, n, largest, sortByIndex, stepsLeft, descending);
        }

        return stepsLeft;
    }
}