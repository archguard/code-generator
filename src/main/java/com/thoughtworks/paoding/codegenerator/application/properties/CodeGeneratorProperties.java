package com.thoughtworks.paoding.codegenerator.application.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "code-generator")
@Data
public class CodeGeneratorProperties {
    private String rootPath;
}
