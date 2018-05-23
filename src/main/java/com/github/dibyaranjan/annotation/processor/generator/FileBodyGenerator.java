package com.github.dibyaranjan.annotation.processor.generator;

import java.util.concurrent.Callable;

public interface FileBodyGenerator<T, V> extends Callable<V> {
    void setAnnotation(T annotation);
}
