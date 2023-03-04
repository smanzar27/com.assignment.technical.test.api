package com.assignment.technical.test.api.stepdefinition;

import com.assignment.technical.test.api.services.IntegrationObjectManager;

public class BaseTest {


    protected final IntegrationObjectManager integrationObjectManager;

    public BaseTest() {

        System.out.println("Test Base Setting Started");
        integrationObjectManager = new IntegrationObjectManager();
        System.out.println("Test Base Setting Completed");
    }

    public IntegrationObjectManager getIntegrationObjectManager() {return integrationObjectManager;}
}
