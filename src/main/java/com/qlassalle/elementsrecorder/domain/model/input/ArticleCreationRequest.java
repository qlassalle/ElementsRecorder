package com.qlassalle.elementsrecorder.domain.model.input;

import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;

@Valid
public record ArticleCreationRequest(String name, String description, int rating,
                                     @URL(message = "{article.url.invalid}") String url) {}
