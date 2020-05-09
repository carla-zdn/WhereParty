package com.example.whereparty.presentation.model.concertAPI;

public class RestConcertResponse {

    private ResultsPage resultsPage;

    public ResultsPage getResultPage() {
        return resultsPage;
    }

    public RestConcertResponse(ResultsPage resultsPage) {
        this.resultsPage = resultsPage;
    }
}
