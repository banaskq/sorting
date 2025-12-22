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
    public List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending) {
        List<List<Object>> list = copyOf(data);
        int n = list.size();
        int stepsLeft = normalizeLimit(limit);

        for (int i = 0; i < n - 1 && stepsLeft > 0; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n && stepsLeft > 0; j++) {
                stepsLeft--;
                if (compareRows(list.get(j), list.get(minIdx), sortByIndex, descending) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                List<Object> tmp = list.get(i);
                list.set(i, list.get(minIdx));
                list.set(minIdx, tmp);
            }
        }
        return list;
    }
}