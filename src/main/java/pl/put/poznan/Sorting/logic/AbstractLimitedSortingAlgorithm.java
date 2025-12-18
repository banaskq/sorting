package pl.put.poznan.Sorting.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLimitedSortingAlgorithm implements SortingAlgorithm {

    protected List<Integer> copyOf(List<Integer> data) {
        return new ArrayList<>(data);
    }

    protected int normalizeLimit(int limit) {
        return (limit <= 0) ? Integer.MAX_VALUE : limit; //<=0 meaning all elements>
    }
}
