package com.copious.training.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsynConfig {

    @Value("${async.core.pool.size}")
    private int poolSize;
    @Value("${async.max.pool.size}")
    private int maxPoolSize;
    @Value("${async.queue.capacity}")
    private int queueCapacity;
    @Value("${async.shutdown}")
    private boolean shutdown;

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor(){
      final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
      taskExecutor.setThreadNamePrefix("async-thread");
      taskExecutor.setCorePoolSize(poolSize);
      taskExecutor.setMaxPoolSize(maxPoolSize);
      taskExecutor.setQueueCapacity(queueCapacity);
      taskExecutor.setWaitForTasksToCompleteOnShutdown(shutdown);
      return taskExecutor;
    }
}
