package com.thoughtworks.paoding.codegenerator.application.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "project-generator")
@Data
public class ProjectGeneratorProperties {
    private String targetPath;
}
