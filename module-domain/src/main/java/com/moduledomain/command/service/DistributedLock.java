package com.moduledomain.command.service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public interface DistributedLock {
    public <T> T lockAndExecute(String[] keys, long waitTime, long leaseTime, TimeUnit unit, Supplier<T> function);
}
