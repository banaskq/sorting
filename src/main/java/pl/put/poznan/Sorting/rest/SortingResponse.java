package pl.put.poznan.Sorting.rest;

import java.util.List;

public class SortingResponse {

    private List<SortingResult> results;

    public SortingResponse(List<SortingResult> results) {
        this.results = results;
    }

    public List<SortingResult> getResults() {
        return results;
    }
}