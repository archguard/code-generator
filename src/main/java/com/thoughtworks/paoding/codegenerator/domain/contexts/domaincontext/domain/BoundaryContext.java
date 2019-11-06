package com.thoughtworks.paoding.codegenerator.domain.contexts.domaincontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class BoundaryContext {
    private String uniqueName;
    private List<Aggregate> aggregates;

    BoundaryContext(String uniqueName) {
        this.uniqueName = uniqueName;
        this.aggregates = new ArrayList<>();
    }

    public List<Aggregate> getAggregates() {
        return List.copyOf(aggregates);
    }
}
