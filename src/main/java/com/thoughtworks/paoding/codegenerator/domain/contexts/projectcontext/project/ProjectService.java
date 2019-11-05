package com.thoughtworks.paoding.codegenerator.domain.contexts.projectcontext.project;

public class ProjectService {

    public Project createProject(String groupName, String artifactName) {
        return new Project(groupName, artifactName);
    }
}
