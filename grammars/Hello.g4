grammar Hello;

compilationUnit
    : classDeclaration?
      EOF
    ;

classDeclaration
    : 'class' identifier classBody
    ;

classBody
    : '{' methodDeclaration* '}'
    ;

methodDeclaration 
    : 'void' identifier formalParameterList methodBody
    ;

methodBody
    : innerBlock
    ;

statement
    : methodCall
    | 'if' expression innerBlock ('else' innerBlock)?
    | 'switch' expression '{' switchBlockStatementGroup* '}'
    ;

methodCall
    : (classIdentifier '.')* identifier informalParameterList ';'
    ;

informalParameterList
    : '(' informalParameters? ')'
    ;

informalParameters
    : identifier (',' identifier)*
    ;

expression
    : '(' identifier ')'
    | '(' parExpression operator parExpression ')'
    ;

switchBlockStatementGroup
    : switchLabel innerBlock
    ;

switchLabel
    : 'case' identifier
    | 'case' (stringLiteral|identifier) 
    | 'default'
    ;

stringLiteral
    : '"'ID'"'
    ;

classIdentifier
    : identifier
    ;

parExpression
    : ( ('!')? identifier | 'true' | 'false' )
    ;

operator
    : '='
    | '<'
    | '>'
    | '<='
    | '>='
    | '=='
    | '++'
    | '--'
    ;

innerBlock
    : '{' statement* '}'
    ;

formalParameterList 
    : '(' formalParameters? ')'
    ;

formalParameters
    : variableDeclaratorId (',' variableDeclaratorId)*
    ;

variableDeclaratorId
    : ID
    ;

identifier
    : ID
    ;

number 
    : NUMBER
    ;

ID : [A-Za-z0-9_]+;
NUMBER : [0..9]+;

WS  :  [ \t\r\n]+ -> skip
    ;
COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;
LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(HIDDEN)
    ;
