package com.thoughtworks.paoding.codegenerator.adapters.inbound.rest.resources;

import antlr.DomainModelBaseListener;
import antlr.DomainModelParser;
import com.thoughtworks.paoding.codegenerator.domain.model.DomainModel;
import com.thoughtworks.paoding.codegenerator.domain.model.DomainModelParent;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static antlr.DomainModelParser.*;

public class DomainParseModelListener extends DomainModelBaseListener {

    private List<DomainModel> domainModels = new ArrayList<>();

    @Override
    public void enterTypeRuleDeclaration(TypeRuleDeclarationContext ctx) {
        super.enterTypeRuleDeclaration(ctx);
    }

    @Override
    public void enterTypeDeclaration(TypeDeclarationContext ctx) {
        super.enterTypeDeclaration(ctx);
        domainModels.add(parse(ctx));
    }

    private DomainModel parse(TypeDeclarationContext ctx) {

        String nameCn = getText(ctx, NameCnDeclarationContext.class);
        String nameEn = getText(ctx, NameEnDeclarationContext.class);
        String classType = getText(ctx, ClassDeclarationContext.class);
        String modelType = getText(ctx, ModelTypeDeclarationContext.class);
        String aggregate = getText(ctx, AggregateDeclarationContext.class);
        List<DomainModelParent> parents = getParents(ctx);

        return new DomainModel(nameCn, nameEn, classType, parents, modelType, aggregate);
    }

    private List<DomainModelParent> getParents(TypeDeclarationContext typeCtx) {
        List<DomainModelParent> parents = new ArrayList<>();

        Optional<ParentListContext> parentListCtx = typeCtx.typeRuleDeclaration().stream()
                .filter(r -> r.getChild(ParentDeclarationContext.class, 0) != null)
                .map(r -> r.getChild(ParentDeclarationContext.class, 0))
                .findAny()
                .map(ParentDeclarationContext::parentList);

        parentListCtx.ifPresent(p -> {
            List<RelationDeclarationContext> relationCtx = p.relationDeclaration();
            for (int i = 0; i < relationCtx.size(); i++) {
                int relation = Integer.parseInt(relationCtx.get(i).IDENTIFIER().getText());
                String name = p.IDENTIFIER(i).getText();
                parents.add(new DomainModelParent(relation, name));
            }
        });

        return parents;
    }

    private String getText(TypeDeclarationContext typeCtx, Class<? extends ParserRuleContext> clazz) {
        return typeCtx.typeRuleDeclaration().stream()
                .filter(r -> r.getChild(clazz, 0) != null)
                .map(r -> r.getChild(clazz, 0))
                .findAny()
                .map(c -> c.getToken(DomainModelParser.IDENTIFIER, 0).getText())
                .orElse(null);
    }

    public List<DomainModel> getDomainModels() {
        return domainModels;
    }
}
