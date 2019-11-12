package com.thoughtworks.paoding.codegenerator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class DomainModel {
    private String nameCn; //中文名称
    private String nameEn; //英文名称
    private String classType; //对象类型
    private List<DomainModelParent> parents; //父属性
    private String modelType; //属性类型
    private String aggregate; //聚合

}
