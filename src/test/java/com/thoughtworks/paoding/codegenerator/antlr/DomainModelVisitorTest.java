package com.thoughtworks.paoding.codegenerator.antlr;

import antlr.DomainModelLexer;
import antlr.DomainModelParser;
import com.thoughtworks.paoding.codegenerator.adapters.inbound.rest.resources.DomainParseModelListener;
import com.thoughtworks.paoding.codegenerator.domain.model.DomainModel;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DomainModelVisitorTest {

    @Test
    public void testModelParseProcedureCalls() {
        String body = "中文名称:任务\n" +
                "英文名称:Job\n" +
                "对象类型:String\n" +
                "父属性:(1)任务,(3)你好\n" +
                "属性类型:ValueObject\n" +
                "聚合:任务\n" +
                "\n" +
                "中文名称:预测任务\n" +
                "英文名称:Job\n" +
                "对象类型:String\n" +
                "父属性:(1)任务\n" +
                "属性类型:Entity\n" +
                "聚合:预测\n" +
                "\n";

        DomainModelLexer lexer = new DomainModelLexer(CharStreams.fromString(body));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DomainModelParser parser = new DomainModelParser(tokens);
        DomainModelParser.CompilationUnitContext tree = parser.compilationUnit();
        DomainParseModelListener domainParseModelListener = new DomainParseModelListener();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(domainParseModelListener, tree);

        List<DomainModel> models = domainParseModelListener.getDomainModels();
        assertEquals(2, models.size());

        assertEquals(2, models.get(0).getParents().size());
        assertEquals("任务", models.get(0).getNameCn());
        assertEquals("Job", models.get(0).getNameEn());
        assertEquals("ValueObject", models.get(0).getModelType());

        assertEquals(1, models.get(1).getParents().size());
        assertEquals(1, models.get(1).getParents().get(0).getRelation());
        assertEquals("任务", models.get(1).getParents().get(0).getParentName());
    }
}
