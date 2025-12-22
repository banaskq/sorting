package pl.put.poznan.Sorting.logic;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MergeSort extends AbstractLimitedSortingAlgorithm {

    @Override
    public String getName() {
        return "merge";
    }

    @Override
    public List<List<Object>> sort(List<List<Object>> data, int sortByIndex, int limit, boolean descending) {
        List<List<Object>> list = copyOf(data);
        int[] stepsLeft = new int[]{ normalizeLimit(limit) };
        return mergeSort(list, sortByIndex, stepsLeft, descending);
    }

    private List<List<Object>> mergeSort(List<List<Object>> list, int sortByIndex,
                                         int[] stepsLeft, boolean descending) {

        if (list.size() <= 1 || stepsLeft[0] <= 0) {
            return list;
        }

        int mid = list.size() / 2;
        List<List<Object>> left = mergeSort(list.subList(0, mid), sortByIndex, stepsLeft, descending);
        List<List<Object>> right = mergeSort(list.subList(mid, list.size()), sortByIndex, stepsLeft, descending);

        return merge(left, right, sortByIndex, stepsLeft, descending);
    }

    private List<List<Object>> merge(List<List<Object>> left, List<List<Object>> right,
                                     int sortByIndex, int[] stepsLeft, boolean descending) {

        List<List<Object>> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size() && stepsLeft[0] > 0) {
            stepsLeft[0]--;
            if (compareRows(left.get(i), right.get(j), sortByIndex, descending) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }
}