package com.personalcapital.montecarlosimulation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${monte_carlo_thread_pool_size:2}")
    public int monteCarloThreadPoolSize;

}
