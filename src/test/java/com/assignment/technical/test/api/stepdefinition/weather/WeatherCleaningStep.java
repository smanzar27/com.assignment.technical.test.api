package com.assignment.technical.test.api.stepdefinition.weather;

import com.assignment.technical.test.api.models.FetchStation;
import com.assignment.technical.test.api.models.Station;
import com.assignment.technical.test.api.services.FetchWeatherStationService;
import com.assignment.technical.test.api.services.RemoveWeatherStationService;
import com.assignment.technical.test.api.stepdefinition.BaseTest;
import com.assignment.technical.test.api.utils.HardAssertion;
import com.assignment.technical.test.api.utils.SoftAssertion;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class WeatherCleaningStep {

    FetchWeatherStationService fetchService;
    RemoveWeatherStationService removeService;
    List<String> ids = new ArrayList<>();

    public WeatherCleaningStep(BaseTest baseTest) {
        fetchService = baseTest.getIntegrationObjectManager().getWeatherStation();
        removeService = baseTest.getIntegrationObjectManager().deleteWeatherStation();
    }

    @When("the user prepare list of station ids registered against specific api-key")
    public void getStationIDs() throws IOException {

        fetchService.get(
                fetchService.setDefaultHeaders(),
                fetchService.setEndPoint(),
                fetchService.setQueryParams()
        );

        Station[] stations = new ObjectMapper().readValue(
                fetchService.getResponseString(),Station[].class
        );
        List<Station> stationList = Arrays.asList(stations);
        stationList.forEach(station -> ids.add(station.getId()));
    }

    @When("the user execute remove register stations against specific api-key")
    public void removeStationByID() {

        ids.forEach(id -> {
            removeService.delete(
                   removeService.setDefaultHeaders(),
                   removeService.setEndPoint(id),
                   removeService.setQueryParams()
            );
            System.out.println("Status Code: " + removeService.getStatusCode());
        });

    }
}
