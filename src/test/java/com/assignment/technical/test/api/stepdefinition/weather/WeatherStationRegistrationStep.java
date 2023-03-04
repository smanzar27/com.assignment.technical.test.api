package com.assignment.technical.test.api.stepdefinition.weather;

import com.assignment.technical.test.api.models.FailureResponse;
import com.assignment.technical.test.api.models.FetchStation;
import com.assignment.technical.test.api.services.FetchWeatherStationService;
import com.assignment.technical.test.api.services.RegisterWeatherStationService;
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
import java.util.Map;


public class WeatherStationRegistrationStep {

    FetchWeatherStationService fetchService;
    RegisterWeatherStationService registerService;
    String requestBody;
    String stationID;
    FailureResponse failureResponse;
    FetchStation fetchStation;

    public WeatherStationRegistrationStep(BaseTest baseTest) {
        fetchService = baseTest.getIntegrationObjectManager().getWeatherStation();
        registerService = baseTest.getIntegrationObjectManager().postWeatherStation();
    }

    @Given("^the user prepare station submit request with following details: (.+) (.+) (.+) (.+) (.+)$")
    public void prepareRequestBody(String external_id, String name, Double latitude, Double longitude, Integer altitude) {

        try {
            requestBody = registerService.prepareRequestBody(external_id,name,latitude,longitude,altitude);
            System.out.println(requestBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @When("^the user execute register a weather station (:?with|without) an API key$")
    public void executeRequestBody(String apiKEY) {
        switch(apiKEY) {
            case "without": registerService.post(
                                registerService.setDefaultHeaders(),
                                registerService.setEndPoint(),
                                requestBody
                            );
                            break;
            case "with":    registerService.post(
                                registerService.setDefaultHeaders(),
                                registerService.setEndPoint(),
                                requestBody,
                                registerService.setQueryParams()
                            );
                            break;
            default: throw new RuntimeException("INVALID TEST DATA");
        }
        System.out.println(registerService.getResponseString());
    }

    @Then("^the user validate (:?register|fetch) a weather station api status code: (.+)$")
    public void validateStatusCode(String apiService, int expectedStatusCode) {

        int actualStatusCode;
        switch(apiService) {
            case "register":    actualStatusCode=registerService.getStatusCode();
                                break;
            case "fetch":       actualStatusCode=fetchService.getStatusCode();
                                break;
            default: throw new RuntimeException("INVALID TEST DATA");
        }
        HardAssertion.assertCompareInteger(actualStatusCode,expectedStatusCode,"API Status Code Verified");
    }

    @And("^the user validates failure response by register a weather station api$")
    public void haveBooksInTheStoreByMap(DataTable dataTable) {
        Map<String, Object>  exceptedDataMap = dataTable.asMap(String.class, Object.class);
        System.out.println(exceptedDataMap);
        try {
             failureResponse = new ObjectMapper().readValue(
                    registerService.getResponseString(),FailureResponse.class
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SoftAssertion softAssertion = new SoftAssertion();
        softAssertion.assertCompareInteger(failureResponse.getCod(), Integer.parseInt((String) exceptedDataMap.get("code")), "verified code");
        softAssertion.assertCompareString(failureResponse.getMessage(), (String) exceptedDataMap.get("message"), "verified message");
        softAssertion.assertAll();
    }

    @When("the user execute fetch a weather station to validate registered weather station")
    public void prepareGetRequestEndPoint() {

        stationID = registerService.getResponseString();
        try {
            FetchStation fetchStation = new ObjectMapper().readValue(
                    registerService.getResponseString(),FetchStation.class
            );
            stationID = fetchStation.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stationID);
        fetchService.get(
                fetchService.setDefaultHeaders(),
                fetchService.setEndPoint(stationID),
                fetchService.setQueryParams()
                );
    }

    @When("^the user verify success response by fetch to validate weather station registration details: (.+) (.+) (.+) (.+) (.+)$")
    public void validateWeatherStationRegistration(String external_id, String name, Double latitude, Double longitude, Integer altitude) {

        try {
             fetchStation = new ObjectMapper().readValue(
                    registerService.getResponseString(),FetchStation.class
            );
            stationID = fetchStation.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SoftAssertion softAssertion = new SoftAssertion();
        softAssertion.assertCompareString(fetchStation.getExternal_id(),external_id, "Verified Registration External ID");
        softAssertion.assertCompareString(fetchStation.getName(),name, "Verified Registration Station Name");
        softAssertion.assertCompareDouble(fetchStation.getLatitude(),latitude, "Verified Registration Latitude");
        softAssertion.assertCompareDouble(fetchStation.getLongitude(),longitude, "Verified Registration Longitude");
        softAssertion.assertCompareInteger(fetchStation.getAltitude(),altitude, "Verified Registration Altitude");
        softAssertion.assertAll();
    }

}
