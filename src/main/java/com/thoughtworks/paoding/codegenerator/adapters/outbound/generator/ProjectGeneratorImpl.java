package com.thoughtworks.paoding.codegenerator.adapters.outbound.generator;

import com.samskivert.mustache.Mustache;
import com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project.Project;
import com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project.ProjectGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class ProjectGeneratorImpl implements ProjectGenerator {

    private final Mustache.Compiler compiler;

    @Value("classpath:templates/gradle/build.gradle.mustache")
    private Resource gradleBuildTemplate;

    @Value("classpath:templates/gradle/settings.gradle.mustache")
    private Resource gradleSettingsTemplate;

    @Value("classpath:templates/gradle/gradlew/gradlew")
    private Resource gradlewShellScript;

    @Value("classpath:templates/gradle/gradlew/gradlew.bat")
    private Resource gradlewBatScript;

    @Value("classpath:templates/gradle/wrapper/gradle-wrapper.jar")
    private Resource gradleWrapperJar;

    @Value("classpath:templates/gradle/wrapper/gradle-wrapper.properties")
    private Resource gradleWrapperProperties;

    @Autowired
    public ProjectGeneratorImpl(Mustache.Compiler compiler) {
        this.compiler = compiler;
    }

    @Override
    public void generate(String targetPath, Project project) throws RuntimeException, IOException {
        generateFileStructure(targetPath, project);
        generateGradleFiles(targetPath, project);
    }

    private void generateGradleFiles(String targetPath, Project project) throws IOException {
        compileGradleFile(targetPath, project, gradleBuildTemplate, "build.gradle");
        compileGradleFile(targetPath, project, gradleSettingsTemplate, "settings.gradle");

        copyGradlewFile(targetPath, project, gradlewShellScript, "gradlew");
        copyGradlewFile(targetPath, project, gradlewBatScript, "gradlew.bat");

        final var gradleWrapperPath = Paths.get(targetPath, project.getArtifact(), "gradle/wrapper");
        Files.createDirectories(gradleWrapperPath);
        copyGradleWrapperFile(gradleWrapperPath.toString(), gradleWrapperJar, "gradle-wrapper.jar");
        copyGradleWrapperFile(gradleWrapperPath.toString(), gradleWrapperProperties, "gradle-wrapper.properties");
    }

    private void copyGradleWrapperFile(String gradleWrapperPath, Resource gradleWrapperJar, String fileName) throws IOException {
        Files.copy(gradleWrapperJar.getFile().toPath(), Paths.get(gradleWrapperPath, fileName), REPLACE_EXISTING);
    }

    private void copyGradlewFile(String targetPath, Project project, Resource resource, String fileName) throws IOException {
        Files.copy(resource.getFile().toPath(), Paths.get(targetPath, project.getArtifact(), fileName), REPLACE_EXISTING);
    }

    private void compileGradleFile(String targetPath, Project project, Resource template, String fileName) throws IOException {
        final var gradleBuildStr = compiler.compile(new FileReader(template.getFile()))
                .execute(project);
        Files.writeString(Paths.get(targetPath, project.getArtifact(), fileName), gradleBuildStr);
    }

    private void generateFileStructure(String targetPath, Project project) throws IOException {
        final var domainPath = mainJavaPath(targetPath, project, "domain");
        final var applicationPath = mainJavaPath(targetPath, project, "application");
        final var inboundAdaptersPath = mainJavaPath(targetPath, project, "adapters/inbound");
        final var outboundAdaptersPath = mainJavaPath(targetPath, project, "adapters/outbound");
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
        return Paths.get(targetPath, project.getArtifact(), "src/main/resource");
    }

    private Path mainJavaPath(String targetPath, Project project, String path) {
        return Paths.get(targetPath, project.getArtifact(), "src/main/java/", packagePath(project), path);
    }

    private String packagePath(Project project) {
        return project.getPackageName().replace('.', '/');
    }
}
