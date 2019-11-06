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
        final var projectPath = targetPath + "/" + project.getArtifact();
        generateFileStructure(projectPath, project);
        generateGradleFiles(projectPath, project);
    }

    private void generateGradleFiles(String projectPath, Project project) throws IOException {
        compileGradleFile(projectPath, project, gradleBuildTemplate, "build.gradle");
        compileGradleFile(projectPath, project, gradleSettingsTemplate, "settings.gradle");

        copyGradlewFile(projectPath, gradlewShellScript, "gradlew");
        copyGradlewFile(projectPath, gradlewBatScript, "gradlew.bat");

        final var gradleWrapperPath = Paths.get(projectPath, "gradle/wrapper");
        Files.createDirectories(gradleWrapperPath);
        copyGradleWrapperFile(gradleWrapperPath.toString(), gradleWrapperJar, "gradle-wrapper.jar");
        copyGradleWrapperFile(gradleWrapperPath.toString(), gradleWrapperProperties, "gradle-wrapper.properties");
    }

    private void copyGradleWrapperFile(String gradleWrapperPath, Resource gradleWrapperJar, String fileName) throws IOException {
        Files.copy(gradleWrapperJar.getFile().toPath(), Paths.get(gradleWrapperPath, fileName), REPLACE_EXISTING);
    }

    private void copyGradlewFile(String projectPath, Resource resource, String fileName) throws IOException {
        Files.copy(resource.getFile().toPath(), Paths.get(projectPath, fileName), REPLACE_EXISTING);
    }

    private void compileGradleFile(String projectPath, Project project, Resource template, String fileName) throws IOException {
        final var gradleBuildStr = compiler.compile(new FileReader(template.getFile()))
                .execute(project);
        Files.writeString(Paths.get(projectPath, fileName), gradleBuildStr);
    }

    private void generateFileStructure(String projectPath, Project project) throws IOException {
        final var domainPath = mainJavaPath(projectPath, project, "domain");
        final var applicationPath = mainJavaPath(projectPath, project, "application");
        final var inboundAdaptersPath = mainJavaPath(projectPath, project, "adapters/inbound");
        final var outboundAdaptersPath = mainJavaPath(projectPath, project, "adapters/outbound");
        final var resourcePath = mainResourcePath(projectPath);

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

    private Path mainResourcePath(String projectPath) {
        return Paths.get(projectPath, "src/main/resource");
    }

    private Path mainJavaPath(String projectPath, Project project, String path) {
        return Paths.get(projectPath, "src/main/java/", packagePath(project), path);
    }

    private String packagePath(Project project) {
        return project.getPackageName().replace('.', '/');
    }
}
