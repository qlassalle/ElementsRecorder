package com.qlassalle.elementsrecorder.domain.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, UUID articleId) {
        super(String.format(message, articleId));
    }
}
