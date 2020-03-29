package com.wrf.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        final var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor imageExecutor() {
        final var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor pushExecutor() {
        final var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.initialize();
        return executor;
    }
}

