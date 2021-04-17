package com.qlassalle.elementsrecorder.integrationtest.utils;

import org.springframework.core.io.ClassPathResource;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ClassPathResources {

    private static final String SEPARATOR = "/";

    private ClassPathResources() {
        throw new UnsupportedOperationException("Utility class shouldn't be initialized");
    }


    public static ClassPathResource classResource(Object currentClass, String path) {
        return new ClassPathResource(joinParts(
                convertDotToSeparator(currentClass.getClass().getPackage().getName()),
                currentClass.getClass().getSimpleName().toLowerCase(),
                path
        ));
    }

    private static String convertDotToSeparator(String path) {
        return path.replaceAll("\\.", SEPARATOR);
    }

    private static String joinParts(String... parts) {
        return Stream.of(parts)
                     .filter(Objects::nonNull)
                     .collect(Collectors.joining(SEPARATOR));
    }
}
