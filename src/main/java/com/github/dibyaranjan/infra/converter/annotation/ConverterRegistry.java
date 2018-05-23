package com.github.dibyaranjan.infra.converter.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to mark the <code>@Convert</code> entries.
 *
 * @author Dibya Ranjan
 */
@Retention(RetentionPolicy.SOURCE)
public @interface ConverterRegistry {
    Convert[] converters();
}
