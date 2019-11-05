package com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project;

import java.io.IOException;

public interface ProjectGenerator {
    void generate(String targetPath, Project project) throws IOException;
}
