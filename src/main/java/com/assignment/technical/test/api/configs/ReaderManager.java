package com.assignment.technical.test.api.configs;

public class ReaderManager {

    private static final ReaderManager readerManager = new ReaderManager();
    protected static APIConfig apiConfigReader;

    private ReaderManager() { }

    public static ReaderManager getInstance( ) {
        return readerManager;
    }
    public APIConfig getApiConfigReader() { return (apiConfigReader == null) ? new APIConfig() : apiConfigReader; }
}
