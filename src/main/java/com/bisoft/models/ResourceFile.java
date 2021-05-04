package com.bisoft.models;

import com.bisoft.App;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public final class ResourceFile {
    private final String nameResource;

    public ResourceFile(String nameResource) {
        this.nameResource = nameResource;
    }

    public String content() {
        InputStream queryStream = App.class.getClassLoader().getResourceAsStream(nameResource);
        return new BufferedReader(
                new InputStreamReader(queryStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

    }
}
