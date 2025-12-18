package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SelectionSort extends AbstractLimitedSortingAlgorithm {

    @Override
    public String getName() {
        return "selection";
    }

    @Override
    public List<Integer> sort(List<Integer> data, int limit) {
        List<Integer> list = copyOf(data);
        int n = list.size();
        int stepsLeft = normalizeLimit(limit);

        for (int i = 0; i < n - 1 && stepsLeft > 0; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n && stepsLeft > 0; j++) {
                stepsLeft--;
                if (list.get(j) < list.get(minIdx)) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int tmp = list.get(i);
                list.set(i, list.get(minIdx));
                list.set(minIdx, tmp);
            }
        }
        return list;
    }
}