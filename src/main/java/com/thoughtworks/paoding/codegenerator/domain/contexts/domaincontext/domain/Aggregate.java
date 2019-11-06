package com.thoughtworks.paoding.codegenerator.domain.contexts.domaincontext.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Aggregate {
    private Entity aggregateRoot;
    private DomainService service;
    private Repository repository;

    public String getUniqueName() {
        return aggregateRoot.getUniqueName();
    }
}
