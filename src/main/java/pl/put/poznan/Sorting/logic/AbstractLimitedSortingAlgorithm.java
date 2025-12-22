package pl.put.poznan.Sorting.logic;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLimitedSortingAlgorithm implements SortingAlgorithm {

    protected List<List<Object>> copyOf(List<List<Object>> data) {
        List<List<Object>> copy = new ArrayList<>();
        for (List<Object> row : data) {
            copy.add(new ArrayList<>(row));
        }
        return copy;
    }

    protected int normalizeLimit(int limit) {
        return (limit <= 0) ? Integer.MAX_VALUE : limit;
    }

    @SuppressWarnings("unchecked")
    protected int compareRows(List<Object> a, List<Object> b, int index, boolean descending) {
        Object va = a.get(index);
        Object vb = b.get(index);

        if (!(va instanceof Comparable) || !(vb instanceof Comparable)) {
            throw new IllegalArgumentException("Kolumna nie zawiera wartości Comparable");
        }

        int cmp = ((Comparable<Object>) va).compareTo(vb);
        return descending ? -cmp : cmp;
    }
}