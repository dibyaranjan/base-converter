package com.github.dibyaranjan.task.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * Abstraction to execute a callable task.
 */
public class TaskExecutor {
    private static Logger logger = Logger.getLogger(TaskExecutor.class.getCanonicalName());
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    @SuppressWarnings("unchecked")
    public <T, V> T submitTask(Callable<V> task) {
        Future<V> future = executorService.submit(task);

        try {
            return (T) future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.warning(ExceptionUtils.getFullStackTrace(e));
        } catch (ExecutionException e) {
            logger.warning(ExceptionUtils.getFullStackTrace(e));
        } catch (TimeoutException e) {
            logger.warning(ExceptionUtils.getFullStackTrace(e));
        }

        return null;
    }
}