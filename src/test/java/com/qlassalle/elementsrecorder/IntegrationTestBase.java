package com.qlassalle.elementsrecorder;

import com.qlassalle.elementsrecorder.exceptions.ReadingTestFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

public abstract class IntegrationTestBase {

    @Autowired
    private WebTestClient client;

    protected String getJsonAsString(String path) {
        try {
            return new String(new ClassPathResource(path).getInputStream().readAllBytes());
        } catch (IOException exception) {
            throw new ReadingTestFileException("Unable to read file " + path);
        }
    }

    protected StatusAssertions postJson(String url, String inputFile) {
        return client.post()
                     .uri(url)
                     .header("Content-Type", "application/json")
                     .bodyValue(getJsonAsString(inputFile))
                     .exchange()
                     .expectStatus();
    }
}
