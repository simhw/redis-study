package com.modulecommon.support;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public interface IDistributedLock {
    public <T> T lockAndExecute(String[] keys, long waitTime, long leaseTime, TimeUnit unit, Supplier<T> function);
}
