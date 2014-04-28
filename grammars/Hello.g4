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
    : block
    ;

statement
    : methodCall
    | 'if' expression block ('else' block)?
    | 'switch' expression '{' switchBlockStatementGroup* '}'
    | 'try' block catchClause+
    ;

catchClause
    : 'catch' '(' identifier ')' block
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
    : switchLabel block
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

block
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
