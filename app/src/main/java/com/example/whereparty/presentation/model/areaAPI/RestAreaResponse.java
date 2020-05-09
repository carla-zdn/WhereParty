package com.example.whereparty.presentation.model.areaAPI;

public class RestAreaResponse {

    private ResultsPage resultsPage;

    public ResultsPage getResultsPageArea() {
        return resultsPage;
    }

    public RestAreaResponse(ResultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }
}
