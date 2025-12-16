package pl.put.poznan.Sorting.rest;

import java.util.List;

public class SortingResult {

    private String algorithm;
    private long timeMillis;
    private List<Integer> sortedData;

    public SortingResult(String algorithm, long timeMillis, List<Integer> sortedData) {
        this.algorithm = algorithm;
        this.timeMillis = timeMillis;
        this.sortedData = sortedData;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public List<Integer> getSortedData() {
        return sortedData;
    }
}