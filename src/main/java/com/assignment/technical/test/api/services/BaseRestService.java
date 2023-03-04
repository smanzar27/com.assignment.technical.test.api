package com.assignment.technical.test.api.services;

import com.assignment.technical.test.api.configs.ReaderManager;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRestService {

    protected URL base_uri;
    protected SessionFilter sessionFilter = new SessionFilter();
    protected RequestSpecification requestSpecification;
    protected Response response;

    public BaseRestService() { }

    public RequestSpecification setBaseRequestSpecification(){
        return RestAssured.given().filter(this.sessionFilter).baseUri(this.base_uri.toString()).relaxedHTTPSValidation();
    }

    public List<Header> setDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add( new Header("Content-Type","application/json"));
        headers.add( new Header("Accept","application/json"));
        return headers;
    }

    public Response get(RequestSpecification requestSpecification, String endPoint){
        System.out.println("EndPoint Execution: "  + endPoint);
        this.requestSpecification = requestSpecification;
        Response response = requestSpecification.get(endPoint);
        this.response = response;
        return response;
    }

    public Response get(List<Header> headers, String endPoint, Map<String,String> params){
        System.out.println("Executing API: "  + endPoint);
        RequestSpecification requestSpecification =     setBaseRequestSpecification()
                .headers(new Headers(headers))
                .queryParams(setUserAPIKEY(params));
        response = get(requestSpecification,endPoint);
        return response;
    }

    public Response post(RequestSpecification requestSpecification, String endPoint){
        System.out.println("EndPoint Execution: "  + endPoint);
        this.requestSpecification = requestSpecification;
        Response response = requestSpecification.post(endPoint);
        this.response = response;
        return response;
    }

    public Response post(List<Header> headers, String endPoint, String body){
        System.out.println("Executing API: "  + endPoint);
        RequestSpecification requestSpecification =     setBaseRequestSpecification()
                .headers(new Headers(headers))
                .body(body);
        response = post(requestSpecification,endPoint);
        return response;
    }

    public Map<String,String> setUserAPIKEY(Map<String,String> params){
        params.put("appid", ReaderManager.getInstance().getApiConfigReader().getAPIKey());
        return params;
    }

    public Response post(List<Header> headers, String endPoint, String body, Map<String,String> params){
        System.out.println("Executing API: "  + endPoint);
        RequestSpecification requestSpecification =     setBaseRequestSpecification()
                .headers(new Headers(headers))
                .queryParams(setUserAPIKEY(params))
                .body(body);
        response = post(requestSpecification,endPoint);
        return response;
    }




    public Response getResponse() { return this.response;}
    public String getResponseString(){ return getResponse().asString(); }
    public int getStatusCode() { return getResponse().getStatusCode();}

}
