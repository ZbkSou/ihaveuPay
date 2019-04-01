package com.ihaveu.ihaveupaybase.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: bkzhou
 * Date: 2018/9/21
 * Description:
 */
public class ThreadManager {
    private static ExecutorService mExecutors = Executors.newSingleThreadExecutor();

    public static void execute(Runnable runnable) {
        if (mExecutors == null) {
            mExecutors = Executors.newSingleThreadExecutor();
        }
        mExecutors.execute(runnable);
    }

    public static void shutdown() {
        if (mExecutors == null) return;
        if (mExecutors.isShutdown()) return;
        mExecutors.shutdownNow();
        mExecutors = null;
    }
}
