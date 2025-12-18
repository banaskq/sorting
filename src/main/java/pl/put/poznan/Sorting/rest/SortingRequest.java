package pl.put.poznan.Sorting.rest;

import java.util.List;

public class SortingRequest {

    // Data to sort
    private List<Integer> data;

    // List of algorithms: (1...6) ["bubble", "quick",...]
    private List<String> algorithms;

    // Limit <= 0 meaning all elements
    private Integer limit;

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<String> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<String> algorithms) {
        this.algorithms = algorithms;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}