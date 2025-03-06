package com.groupe5.backapp;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackAppApplication {

    public static void main(String[] args) {
       // System.setProperty("applicationinsights.configuration.file", "{path}/applicationinsights-dev.json");
        ApplicationInsights.attach();
        SpringApplication.run(BackAppApplication.class, args);
    }

}
