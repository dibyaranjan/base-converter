package com.github.dibyaranjan.annotation.processor.generator.impl;

import java.text.MessageFormat;

import com.github.dibyaranjan.annotation.processor.generator.FileBodyGenerator;
import com.github.dibyaranjan.infra.converter.annotation.Convert;

/**
 * This class will generate the Converter Annotation for the Java file.
 *
 * @author Dibya
 */
public class ConvertAnnotationGenerator implements FileBodyGenerator<Convert, String> {
    private static String ANNOTATION_FORMAT = "@{0}({1},{2})";
    private Convert convert;

    @Override
    public void setAnnotation(Convert annotation) {
        this.convert = annotation;
    }

    @Override
    public String call() throws Exception {
        Class<?> source = convert.source();
        Class<?> target = convert.target();

        String fullyQualifiedName = convert.getClass().getCanonicalName();
        String className = fullyQualifiedName.substring(fullyQualifiedName.lastIndexOf("."));

        return MessageFormat.format(ANNOTATION_FORMAT, fullyQualifiedName, source.getCanonicalName(), target
                .getCanonicalName());
    }
}
