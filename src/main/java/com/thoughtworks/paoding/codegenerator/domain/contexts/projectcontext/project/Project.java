package com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project;

import lombok.Getter;

@Getter
public class Project {
    private String group;
    private String artifact;

    Project(String group, String artifact) {
        this.group = group;
        this.artifact = artifact;
    }

    public String getPackageName() {
        return String.format("%s.%s", group, artifact);
    }
}
