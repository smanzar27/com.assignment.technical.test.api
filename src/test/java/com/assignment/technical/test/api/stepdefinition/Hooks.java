package com.assignment.technical.test.api.stepdefinition;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {

    public Hooks(){ }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        scenario.log("STARTING TEST: " + scenario.getName());
        System.out.println(scenario.getSourceTagNames());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        scenario.log(("COMPLETE TEST: " + scenario.getName()));
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

    }

}
