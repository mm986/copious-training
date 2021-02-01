package com.copious.training.service;

import java.util.concurrent.ExecutionException;

public interface AsyncService {
    void asyncServiceDemo() throws ExecutionException, InterruptedException;
}
