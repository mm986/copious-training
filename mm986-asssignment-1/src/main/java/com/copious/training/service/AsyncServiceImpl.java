package com.copious.training.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("asyncExecutor")
    public void asyncServiceDemo() throws ExecutionException, InterruptedException {
        Thread.sleep(30000);
        System.out.println("Started executing Async Service");
    }

//    @Async("asyncExecutor")
//    public Future<String> asyncServiceFuture() throws ExecutionException, InterruptedException {
//        System.out.println("Started executing Future Async Service");
//        return new Future();
//    }
}
