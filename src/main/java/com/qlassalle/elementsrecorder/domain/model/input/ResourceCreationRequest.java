package com.qlassalle.elementsrecorder.domain.model.input;

import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Set;

@Valid
public record ResourceCreationRequest(String name, String description, int rating,
                                      @URL(message = "{resource.url.invalid}") String url, Set<String> tags) {
    public ResourceCreationRequest(String name, String description, int rating, String url) {
        this(name, description, rating, url, Collections.emptySet());
    }
}
