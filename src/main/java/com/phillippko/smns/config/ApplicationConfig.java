package com.phillippko.smns.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "smns")
public class ApplicationConfig {
    String geocodingUrl;
    String applicationApi;
}

