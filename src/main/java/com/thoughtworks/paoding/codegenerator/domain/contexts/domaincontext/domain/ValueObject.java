package com.thoughtworks.paoding.codegenerator.domain.contexts.domaincontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class ValueObject {
    private String uniqueName;
    private List<ValueObject> subValueObjects;

    public ValueObject(String uniqueName) {
        this.uniqueName = uniqueName;
        this.subValueObjects = new ArrayList<>();
    }

    public List<ValueObject> getSubValueObjects() {
        return List.copyOf(subValueObjects);
    }
}
