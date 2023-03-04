package com.assignment.technical.test.api.stepdefinition;

import com.assignment.technical.test.api.utils.Constant;
import com.assignment.technical.test.api.utils.FileOperation;
import io.cucumber.java.*;

import java.util.List;


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

    @BeforeStep
    public void beforeStep(Scenario scenario)  {
        FileOperation.createFile(Constant.CUCUMBER_STEP);
    }

    @AfterStep
    public void afterStep(Scenario scenario){

        List<String> listStringLine = FileOperation.readFile(Constant.CUCUMBER_STEP);
        listStringLine.forEach(scenario::log);
        FileOperation.deleteFile(Constant.CUCUMBER_STEP);

    }

}
