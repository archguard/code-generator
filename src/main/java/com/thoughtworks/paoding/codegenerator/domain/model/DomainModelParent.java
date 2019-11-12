package com.thoughtworks.paoding.codegenerator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainModelParent {
    private int relation;
    private String parentName;
}
