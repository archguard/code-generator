package com.thoughtworks.paoding.codegenerator.domain.contexts.domaincontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Domain {
    private List<BoundaryContext> contexts;

    Domain() {
        contexts = new ArrayList<>();
    }

    public List<BoundaryContext> getContexts() {
        return List.copyOf(contexts);
    }
}
