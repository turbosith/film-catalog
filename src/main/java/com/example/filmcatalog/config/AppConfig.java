package com.example.filmcatalog.config;

import com.example.filmcatalog.metrics.InfluxDBConnector;
import com.example.filmcatalog.metrics.SimpleMetrics;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ConfigurationProperties("app")
public class AppConfig {
    @Bean
    public SimpleMetrics getSimpleMetrics(){
        return new SimpleMetrics(getInfluxDBConnector());
    }
    @Bean
    public InfluxDBConnector getInfluxDBConnector(){
        return new InfluxDBConnector(
                "http://localhost:8086",
                "-9oAA-BvaBkakzkIP7vepVvPUPKesnD7rOzfZ31C2wThm-ytsPJzfGKkaRpoaxYCe60i6NC9GFVw8qqBoZKe0g==",
                "РТУ МИРЭА",
                "testcounter"
        );
    }}



