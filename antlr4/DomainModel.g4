grammar DomainModel;

compilationUnit: typeBlockDeclaration NEWSECTION* (typeBlockDeclaration NEWSECTION*)* EOF;

typeBlockDeclaration: typeDeclaration NEWSECTION?;

typeDeclaration: typeRuleDeclaration (NEWSECTION typeRuleDeclaration)*;

typeRuleDeclaration
    : nameCnDeclaration
    | nameEnDeclaration
    | classDeclaration
    | modelTypeDeclaration
    | aggregateDeclaration
    | parentDeclaration
    ;

nameCnDeclaration: NAME_CN COLON IDENTIFIER;
nameEnDeclaration: NAME_EN COLON IDENTIFIER;
classDeclaration: CLASS_TYPE COLON IDENTIFIER;
modelTypeDeclaration: MODEL_TYPE COLON IDENTIFIER;
aggregateDeclaration: AGGREGATE COLON IDENTIFIER;
parentDeclaration: PARENT COLON parentList;

relationDeclaration: '('IDENTIFIER')';
parentList
    : relationDeclaration IDENTIFIER (',' WS? relationDeclaration IDENTIFIER)*
    ;

COLON: ':' | '：';
NAME_CN: '中文名称';
NAME_EN: '英文名称';
CLASS_TYPE: '对象类型';
PARENT: '父属性';
MODEL_TYPE: '属性类型';
AGGREGATE: '聚合';
LBRACK: '[';
RBRACK: ']';
COMMA: ',' | '，';

NEWSECTION:             '\r\n'|'\r'|'\n';
WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

IDENTIFIER: LetterOrDigit LetterOrDigit*;

fragment LetterOrDigit
    : Letter
    | [0-9]
    ;

fragment Letter
    : [a-zA-Z$_ ] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;
