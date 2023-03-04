package com.assignment.technical.test.api.services;

public class IntegrationObjectManager {

    private RegisterWeatherStationService registerWeatherStationService;
    private FetchWeatherStationService fetchWeatherStationService;

    public IntegrationObjectManager() {  }

    public RegisterWeatherStationService postWeatherStation(){ return (registerWeatherStationService == null) ? registerWeatherStationService = new RegisterWeatherStationService() : registerWeatherStationService; }
    public FetchWeatherStationService getWeatherStation(){ return (fetchWeatherStationService == null) ? fetchWeatherStationService = new FetchWeatherStationService() : fetchWeatherStationService; }

}
