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
    public List<Integer> sort(List<Integer> data, int limit) {
        List<Integer> list = copyOf(data);
        int[] stepsLeft = new int[]{ normalizeLimit(limit) };
        return mergeSort(list, stepsLeft);
    }

    private List<Integer> mergeSort(List<Integer> list, int[] stepsLeft) {
        if (list.size() <= 1 || stepsLeft[0] <= 0) {
            return list;
        }
        int mid = list.size() / 2;
        List<Integer> left = mergeSort(list.subList(0, mid), stepsLeft);
        List<Integer> right = mergeSort(list.subList(mid, list.size()), stepsLeft);
        return merge(left, right, stepsLeft);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right, int[] stepsLeft) {
        List<Integer> result = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;

        while (i < left.size() && j < right.size() && stepsLeft[0] > 0) {
            stepsLeft[0]--;
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        while (i < left.size() && stepsLeft[0] > 0) {
            result.add(left.get(i++));
        }
        while (j < right.size() && stepsLeft[0] > 0) {
            result.add(right.get(j++));
        }

        // If the limit is reached, all unsorted data is randomly appended.

        while (i < left.size()) {
            result.add(left.get(i++));
        }
        while (j < right.size()) {
            result.add(right.get(j++));
        }
        return result;
    }
}