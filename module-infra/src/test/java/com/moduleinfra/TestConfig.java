package com.moduleinfra;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.moduledomain", "com.moduleinfra"})
public class TestConfig {
}