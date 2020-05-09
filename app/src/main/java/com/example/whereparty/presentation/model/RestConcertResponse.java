package com.example.whereparty.presentation.model;

public class RestConcertResponse {

    private ResultsPage resultsPage;

    public ResultsPage getResultPage() {
        return resultsPage;
    }

    public RestConcertResponse(ResultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }
}
