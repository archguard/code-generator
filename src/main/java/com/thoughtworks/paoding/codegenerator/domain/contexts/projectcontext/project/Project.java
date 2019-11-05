package com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project;

import lombok.Getter;

@Getter
public class Project {
    private String groupName;
    private String artifactName;

    Project(String groupName, String artifactName) {
        this.groupName = groupName;
        this.artifactName = artifactName;
    }

    public String getPackageName() {
        return String.format("%s.%s", groupName, artifactName);
    }
}
