package com.qlassalle.elementsrecorder.integrationtest.utils;

import lombok.Value;

@Value
public class HttpTestCase {
    String url;
    String inputFilename;
    int statusCode;
    String outputFilename;
    String name;

    public HttpTestCase(String url, String filename, int statusCode, String name) {
        this.url = url;
        this.inputFilename = filename;
        this.outputFilename = filename;
        this.statusCode = statusCode;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
