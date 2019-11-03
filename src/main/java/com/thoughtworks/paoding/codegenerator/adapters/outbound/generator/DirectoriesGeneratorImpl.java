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
    public void generate(String rootPath, String groupName, String artifactName) throws RuntimeException, IOException {
        final var packagePath = generatePackagePath(groupName, artifactName);
        final var domainPath = Paths.get(rootPath, "src/main/java/" + packagePath + "/domain");
        final var applicationPath = Paths.get(rootPath, "src/main/java/" + packagePath + "/application");
        final var inboundAdaptersPath = Paths.get(rootPath, "src/main/java/" + packagePath + "/adapters/inbound");
        final var outboundAdaptersPath = Paths.get(rootPath, "src/main/java/" + packagePath + "/adapters/outbound");
        final var resourcePath = Paths.get(rootPath, "src/main/resource");

        final var paths = List.of(domainPath, applicationPath, inboundAdaptersPath, outboundAdaptersPath, resourcePath);
        for (var path : paths) {
            Files.createDirectories(path);
        }
    }

    private String generatePackagePath(String groupName, String artifactName) {
        return String.format("%s/%s", groupName.replace('.', '/'), artifactName);
    }
}
