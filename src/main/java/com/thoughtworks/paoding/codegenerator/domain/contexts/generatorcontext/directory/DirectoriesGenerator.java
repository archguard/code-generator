package com.thoughtworks.paoding.codegenerator.domain.contexts.generatorcontext.directory;

import java.io.IOException;

public interface DirectoriesGenerator {
    void generate(String rootPath) throws IOException;
}
