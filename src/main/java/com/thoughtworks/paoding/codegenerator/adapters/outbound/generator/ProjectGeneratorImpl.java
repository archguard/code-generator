package com.thoughtworks.paoding.codegenerator.adapters.outbound.generator;

import com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project.Project;
import com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project.ProjectGenerator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ProjectGeneratorImpl implements ProjectGenerator {

    @Override
    public void generate(String targetPath, Project project) throws RuntimeException, IOException {
        final var domainPath = mainJavaPath(targetPath, project, "/domain");
        final var applicationPath = mainJavaPath(targetPath, project, "/application");
        final var inboundAdaptersPath = mainJavaPath(targetPath, project, "/adapters/inbound");
        final var outboundAdaptersPath = mainJavaPath(targetPath, project, "/adapters/outbound");
        final var resourcePath = mainResourcePath(targetPath, project);

        final var paths = List.of(
                domainPath,
                applicationPath,
                inboundAdaptersPath,
                outboundAdaptersPath,
                resourcePath
        );

        for (var path : paths) {
            Files.createDirectories(path);
        }
    }

    private Path mainResourcePath(String targetPath, Project project) {
        return Paths.get(targetPath, project.getArtifactName(), "src/main/resource");
    }

    private Path mainJavaPath(String targetPath, Project project, String path) {
        return Paths.get(targetPath, project.getArtifactName(), "src/main/java/", packagePath(project), path);
    }

    private String packagePath(Project project) {
        return project.getPackageName().replace('.', '/');
    }
}
