package com.pburgov.springdelivery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource( "classpath:configprops.properties" )
@ConfigurationProperties( prefix = "delivery" )
public class ConfigProperties {

    private String fotosPath;

    private String testJsonPath;

    public String getFotosPath() {
        return fotosPath;
    }

    public void setFotosPath( String fotosPath ) {
        this.fotosPath = fotosPath;
    }

    public String getTestJsonPath() { return testJsonPath; }

    public void setTestJsonPath( String testJsonPath ) {
        this.testJsonPath = testJsonPath;
    }

}
