package com.assignment.technical.test.api.services;

import com.assignment.technical.test.api.configs.ReaderManager;
import com.assignment.technical.test.api.exception.InvalidUserInputException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class FetchWeatherStationService extends BaseRestService {

    public final String apiEndPoint = "/data/3.0/stations/";

    public FetchWeatherStationService(){
        try {
            base_uri = new URL(ReaderManager.getInstance().getApiConfigReader().getAPIBaseURI());
        } catch (MalformedURLException e) {
            throw new InvalidUserInputException("invalid base uri provided");
        }
    }

    public String  setEndPoint(String recordID) {
        return apiEndPoint.concat(recordID);
    }

    public Map<String,String> setQueryParams(){
        return new HashMap<>();
    }

}
