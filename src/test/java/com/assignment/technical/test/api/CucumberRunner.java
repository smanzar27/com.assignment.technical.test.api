package com.assignment.technical.test.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {  "pretty"
                    ,"html:target/cucumber"
                    ,"json:target/cucumber.json"
                    ,"junit:target/cucumber.xml"
                    }
        ,   features = "src/test/resources/feature"
        ,   glue = {"com.assignment.technical.test.api.stepdefinition"}
        ,   tags = "@Station"
        ,   monochrome = true
)

public class CucumberRunner extends AbstractTestNGCucumberTests {

}
