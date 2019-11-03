package com.thoughtworks.paoding.codegenerator.adapters.outbound.generator;

import com.thoughtworks.paoding.codegenerator.domain.contexts.generatorcontext.directory.DirectoriesGenerator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DirectoriesGeneratorImpl implements DirectoriesGenerator {

    @Override
    public void generate(String rootPath) throws RuntimeException, IOException {
        final var domainPath = Paths.get(rootPath, "src/main/java/test/package/domain");
        final var applicationPath = Paths.get(rootPath, "src/main/java/test/package/application");
        final var inboundAdaptersPath = Paths.get(rootPath, "src/main/java/test/package/adapters/inbound");
        final var outboundAdaptersPath = Paths.get(rootPath, "src/main/java/test/package/adapters/outbound");
        final var resourcePath = Paths.get(rootPath, "src/main/resource");

        final var paths = List.of(domainPath, applicationPath, inboundAdaptersPath, outboundAdaptersPath, resourcePath);
        for (var path : paths) {
            Files.createDirectories(path);
        }
    }
}
