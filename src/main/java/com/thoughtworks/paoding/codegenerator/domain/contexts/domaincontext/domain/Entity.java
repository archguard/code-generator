package com.thoughtworks.paoding.codegenerator.domain.contexts.domaincontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Entity {
    private String uniqueName;
    private List<Entity> subEntities;
    private List<ValueObject> valueObjects;

    Entity(String uniqueName) {
        this.uniqueName = uniqueName;
        this.subEntities = new ArrayList<>();
        this.valueObjects = new ArrayList<>();
    }

    public List<Entity> getSubEntities() {
        return List.copyOf(subEntities);
    }

    public List<ValueObject> getValueObjects() {
        return List.copyOf(valueObjects);
    }
}
