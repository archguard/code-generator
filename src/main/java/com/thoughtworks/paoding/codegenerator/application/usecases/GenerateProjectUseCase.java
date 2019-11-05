package com.thoughtworks.paoding.codegenerator.application.usecases;

import com.thoughtworks.paoding.codegenerator.application.properties.ProjectGeneratorProperties;
import com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project.ProjectGenerator;
import com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GenerateProjectUseCase {
    private final ProjectGeneratorProperties properties;
    private final ProjectGenerator generator;
    private final ProjectService service;

    @Autowired
    public GenerateProjectUseCase(ProjectGeneratorProperties properties, ProjectGenerator generator) {
        this.properties = properties;
        this.generator = generator;
        this.service = new ProjectService();
    }

    public void apply(String groupName, String artifactName) throws IOException {
        generator.generate(properties.getTargetPath(), service.createProject(groupName, artifactName));
    }

}
