package com.qlassalle.elementsrecorder.domain.model.input;

import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;

@Valid
public record ResourceCreationRequest(String name, String description, int rating,
                                      @URL(message = "{resource.url.invalid}") String url) {}
