package com.qlassalle.elementsrecorder.domain.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, UUID resourceId) {
        super(String.format(message, resourceId));
    }
}
