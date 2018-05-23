package com.github.dibyaranjan.annotation.processor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;

import com.github.dibyaranjan.infra.converter.annotation.Convert;
import com.github.dibyaranjan.infra.converter.annotation.ConverterRegistry;

/**
 * Annotation processor for generating converter classes. To be able to use this class, there must be at lease one
 * instance of <code>@ConverterRegistry</code> in the target package.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ConvertAnnotationProcessor extends AbstractProcessor {
    private static final Logger logger = Logger.getLogger(ConvertAnnotationProcessor.class.getCanonicalName());

    /**
     * !IMPORTANT! Do not get carried away with the fancy Annotation for SupportedTypes. Having class reference makes it
     * easy for changing name.
     *
     * @return A unique set of Strings of fully qualified class names
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        String convertName = ConverterRegistry.class.getCanonicalName();
        Set<String> supportedAnnotations = new LinkedHashSet<>();
        supportedAnnotations.add(convertName);
        return supportedAnnotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        logger.info("Processing annotations : " + getSupportedAnnotationTypes());

        for (TypeElement typeElement : annotations) {
            List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                DeclaredType declaredType = annotationMirror.getAnnotationType();
                Convert annotation = declaredType.getAnnotation(Convert.class);
            }
        }

        return true;
    }

    public ExecutableElement getExecutableElement(final TypeElement typeElement, final Name name) {
        TypeElement te = typeElement;
        do {
            te = (TypeElement) processingEnv.getTypeUtils().asElement(te.getSuperclass());
            if (te != null) {
                for (ExecutableElement ee : ElementFilter.methodsIn(te.getEnclosedElements())) {
                    if (name.equals(ee.getSimpleName()) && ee.getParameters().isEmpty()) {
                        return ee;
                    }
                }
            }
        } while (te != null);
        return null;

    }

}
