package com.assignment.technical.test.api.services;

import com.assignment.technical.test.api.configs.ReaderManager;
import com.assignment.technical.test.api.exception.InvalidUserInputException;
import com.assignment.technical.test.api.models.RegisterStation;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class RegisterWeatherStationService extends BaseRestService {

    public final String apiEndPoint = "/data/3.0/stations";

    public RegisterWeatherStationService(){
        try {
            base_uri = new URL(ReaderManager.getInstance().getApiConfigReader().getAPIBaseURI());
        } catch (MalformedURLException e) {
            throw new InvalidUserInputException("invalid base uri provided");
        }
    }

    public String  setEndPoint() {
        return apiEndPoint;
    }

    public String  prepareRequestBody(String id, String name, Double latitude, Double longitude, Integer altitude) throws IOException {
        return new ObjectMapper().writeValueAsString(
                new RegisterStation(
                        id,
                        name,
                        latitude,
                        longitude,
                        altitude
                ));
    }

    public Map<String,String> setQueryParams(){
        return new HashMap<>();
    }
}
