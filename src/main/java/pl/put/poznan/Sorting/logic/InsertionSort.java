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
    public List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending) {
        List<List<Object>> list = copyOf(data);
        int stepsLeft = normalizeLimit(limit);

        for (int i = 1; i < list.size() && stepsLeft > 0; i++) {
            List<Object> key = list.get(i);
            int j = i - 1;

            while (j >= 0 && stepsLeft > 0 &&
                    compareRows(list.get(j), key, sortByIndex, descending) > 0) {
                stepsLeft--;
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        return list;
    }
}