package com.icloud.test.config;

import java.util.Properties;

public class TestConfig {
    private static final TestConfig INSTANCE = new TestConfig("config.properties");

    private Properties props;

    private TestConfig(String configPath){
        this.props = new Properties();

        try {
            this.props.load(TestConfig.class.getClassLoader().getResourceAsStream(configPath));
        }catch (Exception e ){
            throw new RuntimeException("Could not loud file!");
        }

    }

    public static TestConfig getInstance(){
        return INSTANCE;
    }

    public String getMainURL(){
        return this.props.getProperty("mainUrl");
    }
}
