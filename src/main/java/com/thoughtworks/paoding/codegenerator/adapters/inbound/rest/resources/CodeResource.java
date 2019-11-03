package com.thoughtworks.paoding.codegenerator.adapters.inbound.rest.resources;

import com.thoughtworks.paoding.codegenerator.application.usecases.GenerateDirectoriesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/code", produces = APPLICATION_JSON_VALUE)
public class CodeResource {

    private final GenerateDirectoriesUseCase generateDirectoriesUseCase;

    @Autowired
    public CodeResource(GenerateDirectoriesUseCase generateDirectoriesUseCase) {
        this.generateDirectoriesUseCase = generateDirectoriesUseCase;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void post() throws IOException {
        generateDirectoriesUseCase.apply();
    }
}
