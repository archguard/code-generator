package com.thoughtworks.paoding.codegenerator.application.usecases;

import com.thoughtworks.paoding.codegenerator.application.properties.CodeGeneratorProperties;
import com.thoughtworks.paoding.codegenerator.domain.contexts.generatorcontext.directory.DirectoriesGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GenerateDirectoriesUseCase {
    private final CodeGeneratorProperties properties;
    private final DirectoriesGenerator generator;

    @Autowired
    public GenerateDirectoriesUseCase(CodeGeneratorProperties properties, DirectoriesGenerator generator) {
        this.properties = properties;
        this.generator = generator;
    }

    public void apply(String groupName, String artifactName) throws IOException {
        generator.generate(properties.getRootPath(), groupName, artifactName);
    }
}
