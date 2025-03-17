package com.moduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.moduleapi", "com.moduledomain", "com.moduleinfra",})
public class ModuleApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }
}
