# Code Generator - 代码生成服务

当前的代码生成服务仅支持生成基于以下技术栈的代码：

- Java

## 技术选型

模板引擎
- [Mustache](https://github.com/samskivert/jmustache)

解析器生成器
- [ANTLR](https://github.com/antlr/antlr4)

## 生成优先级

根据与技术实现细节的耦合度和开发实现难度进行排序（技术实现细节耦合度低且实现难度低的排在前），本服务将按顺序实现以下代码的生成：

* [ ]  聚合、聚合根、实体、值对象
* [ ]  领域服务和其他领域层基础代码
* [ ]  构建代码和分层架构文件夹结构