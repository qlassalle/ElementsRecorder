package com.qlassalle.elementsrecorder.integrationtest;

import com.qlassalle.elementsrecorder.integrationtest.exceptions.ReadingTestFileException;
import com.qlassalle.elementsrecorder.integrationtest.utils.ClassPathResources;
import com.qlassalle.elementsrecorder.integrationtest.utils.TruncateTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@TruncateTables
public abstract class IntegrationTestBase {

    @Autowired
    private WebTestClient client;

    protected static final String INPUT_BASE_PATH = "input/";
    protected static final String OUTPUT_BASE_PATH = "output/";

    protected String getJsonAsString(String path) {
        try {
            return new String(ClassPathResources.classResource(this, path).getInputStream().readAllBytes(),
                              StandardCharsets.UTF_8);
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
