package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertionSort extends AbstractLimitedSortingAlgorithm {

    @Override
    public String getName() {
        return "insertion";
    }

    @Override
    public List<Integer> sort(List<Integer> data, int limit) {
        List<Integer> list = copyOf(data);
        int stepsLeft = normalizeLimit(limit);

        for (int i = 1; i < list.size() && stepsLeft > 0; i++) {
            int key = list.get(i);
            int j = i - 1;

            while (j >= 0 && stepsLeft > 0 && list.get(j) > key) {
                stepsLeft--;
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        return list;
    }
}