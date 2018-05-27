/*
 [The "BSD licence"]
 Copyright (j2c.c) 2013 Sam Harwell
 All rights reserved.
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.
 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/** C 2011 grammar built from the C11 Spec */
grammar C;

@header {
package c2j.c;
}

primaryExpression
    :   Identifier
    |   Constant
    |   StringLiteral+
    |   LeftParen expression RightParen
    |   genericSelection
    |   '__extension__'? LeftParen compoundStatement RightParen // Blocks (GCC extension)
    |   '__builtin_va_arg' LeftParen unaryExpression Comma typeName RightParen
    |   '__builtin_offsetof' LeftParen typeName Comma unaryExpression RightParen
    ;

genericSelection
    :   Generic LeftParen assignmentExpression Comma genericAssocList RightParen
    ;

genericAssocList
    :   genericAssociation
    |   genericAssocList Comma genericAssociation
    ;

genericAssociation
    :   typeName Colon assignmentExpression
    |   Default Colon assignmentExpression
    ;

postfixExpression
    :   primaryExpression
    |   postfixExpression LeftBracket expression RightBracket
    |   postfixExpression LeftParen argumentExpressionList? RightParen
    |   postfixExpression Dot Identifier
    |   postfixExpression Arrow Identifier
    |   postfixExpression PlusPlus
    |   postfixExpression MinusMinus
    |   LeftParen typeName RightParen LeftBrace initializerList RightBrace
    |   LeftParen typeName RightParen LeftBrace initializerList Comma RightBrace
    |   '__extension__' LeftParen typeName RightParen LeftBrace initializerList RightBrace
    |   '__extension__' LeftParen typeName RightParen LeftBrace initializerList Comma RightBrace
    ;

argumentExpressionList
    :   assignmentExpression
    |   argumentExpressionList Comma assignmentExpression
    ;

unaryExpression
    :   postfixExpression
    |   PlusPlus unaryExpression
    |   MinusMinus unaryExpression
    |   unaryOperator castExpression
    |   Sizeof unaryExpression
    |   Sizeof LeftParen typeName RightParen
    |   Alignof LeftParen typeName RightParen
    |   AndAnd Identifier // GCC extension address of label
    ;

unaryOperator
    :   And | Star | Plus | Minus | Tilde | Not
    ;

castExpression
    :   LeftParen typeName RightParen castExpression
    |   '__extension__' LeftParen typeName RightParen castExpression
    |   unaryExpression
    |   DigitSequence // for
    ;

multiplicativeExpression
    :   castExpression
    |   multiplicativeExpression Star castExpression
    |   multiplicativeExpression Div castExpression
    |   multiplicativeExpression Mod castExpression
    ;

additiveExpression
    :   multiplicativeExpression
    |   additiveExpression Plus multiplicativeExpression
    |   additiveExpression Minus multiplicativeExpression
    ;

shiftExpression
    :   additiveExpression
    |   shiftExpression LeftShift additiveExpression
    |   shiftExpression RightShift additiveExpression
    ;

relationalExpression
    :   shiftExpression
    |   relationalExpression Less shiftExpression
    |   relationalExpression Greater shiftExpression
    |   relationalExpression LessEqual shiftExpression
    |   relationalExpression GreaterEqual shiftExpression
    ;

equalityExpression
    :   relationalExpression
    |   equalityExpression Equal relationalExpression
    |   equalityExpression NotEqual relationalExpression
    ;

andExpression
    :   equalityExpression
    |   andExpression And equalityExpression
    ;

exclusiveOrExpression
    :   andExpression
    |   exclusiveOrExpression Caret andExpression
    ;

inclusiveOrExpression
    :   exclusiveOrExpression
    |   inclusiveOrExpression Or exclusiveOrExpression
    ;

logicalAndExpression
    :   inclusiveOrExpression
    |   logicalAndExpression AndAnd inclusiveOrExpression
    ;

logicalOrExpression
    :   logicalAndExpression
    |   logicalOrExpression OrOr logicalAndExpression
    ;

conditionalExpression
    :   logicalOrExpression (Question expression Colon conditionalExpression)?
    ;

assignmentExpression
    :   conditionalExpression
    |   unaryExpression assignmentOperator assignmentExpression
    |   DigitSequence // for
    ;

assignmentOperator
    :   Assign | StarAssign | DivAssign | ModAssign | PlusAssign | MinusAssign | LeftShiftAssign
    | RightShiftAssign | AndAssign | XorAssign | OrAssign
    ;

expression
    :   assignmentExpression
    |   expression Comma assignmentExpression
    ;

constantExpression
    :   conditionalExpression
    ;

declaration
    :   declarationSpecifiers initDeclaratorList Semi
	| 	declarationSpecifiers Semi
    |   staticAssertDeclaration
    ;

declarationSpecifiers
    :   declarationSpecifier+
    ;

declarationSpecifiers2
    :   declarationSpecifier+
    ;

declarationSpecifier
    :   storageClassSpecifier
    |   typeSpecifier
    |   typeQualifier
    |   functionSpecifier
    |   alignmentSpecifier
    ;

initDeclaratorList
    :   initDeclarator
    |   initDeclaratorList Comma initDeclarator
    ;

initDeclarator
    :   declarator
    |   declarator Assign initializer
    ;

storageClassSpecifier
    :   Typedef
    |   Extern
    |   Static
    |   ThreadLocal
    |   Auto
    |   Register
    ;

typeSpecifier
    :   (Void
    |   Char
    |   Short
    |   Int
    |   Long
    |   Float
    |   Double
    |   Signed
    |   Unsigned
    |   Bool
    |   Complex
    |   '__m128'
    |   '__m128d'
    |   '__m128i')
    |   '__extension__' LeftParen ('__m128' | '__m128d' | '__m128i') RightParen
    |   atomicTypeSpecifier
    |   structOrUnionSpecifier
    |   enumSpecifier
    |   typedefName
    |   '__typeof__' LeftParen constantExpression RightParen // GCC extension
    |   typeSpecifier pointer
    ;

structOrUnionSpecifier
    :   structOrUnion Identifier? LeftBrace structDeclarationList RightBrace
    |   structOrUnion Identifier
    ;

structOrUnion
    :   Struct
    |   Union
    ;

structDeclarationList
    :   structDeclaration
    |   structDeclarationList structDeclaration
    ;

structDeclaration
    :   specifierQualifierList structDeclaratorList? Semi
    |   staticAssertDeclaration
    ;

specifierQualifierList
    :   typeSpecifier specifierQualifierList?
    |   typeQualifier specifierQualifierList?
    ;

structDeclaratorList
    :   structDeclarator
    |   structDeclaratorList Comma structDeclarator
    ;

structDeclarator
    :   declarator
    |   declarator? Colon constantExpression
    ;

enumSpecifier
    :   Enum Identifier? LeftBrace enumeratorList RightBrace
    |   Enum Identifier? LeftBrace enumeratorList Comma RightBrace
    |   Enum Identifier
    ;

enumeratorList
    :   enumerator
    |   enumeratorList Comma enumerator
    ;

enumerator
    :   enumerationConstant
    |   enumerationConstant Assign constantExpression
    ;

enumerationConstant
    :   Identifier
    ;

atomicTypeSpecifier
    :   Atomic LeftParen typeName RightParen
    ;

typeQualifier
    :   Const
    |   Restrict
    |   Volatile
    |   Atomic
    ;

functionSpecifier
    :   (Inline
    |   Noreturn
    |   '__inline__' // GCC extension
    |   '__stdcall')
    |   gccAttributeSpecifier
    |   '__declspec' LeftParen Identifier RightParen
    ;

alignmentSpecifier
    :   Alignas LeftParen typeName RightParen
    |   Alignas LeftParen constantExpression RightParen
    ;

declarator
    :   pointer? directDeclarator gccDeclaratorExtension*
    ;

directDeclarator
    :   Identifier
    |   LeftParen declarator RightParen
    |   directDeclarator LeftBracket typeQualifierList? assignmentExpression? RightBracket
    |   directDeclarator LeftBracket Static typeQualifierList? assignmentExpression RightBracket
    |   directDeclarator LeftBracket typeQualifierList Static assignmentExpression RightBracket
    |   directDeclarator LeftBracket typeQualifierList? Star RightBracket
    |   directDeclarator LeftParen parameterTypeList RightParen
    |   directDeclarator LeftParen identifierList? RightParen
    |   Identifier Colon DigitSequence  // bit field
    |   LeftParen typeSpecifier? pointer directDeclarator RightParen // function pointer like: (__cdecl *f)
    ;

gccDeclaratorExtension
    :   '__asm' LeftParen StringLiteral+ RightParen
    |   gccAttributeSpecifier
    ;

gccAttributeSpecifier
    :   '__attribute__' LeftParen LeftParen gccAttributeList RightParen RightParen
    ;

gccAttributeList
    :   gccAttribute (Comma gccAttribute)*
    |   // empty
    ;

gccAttribute
    :   ~(Comma | LeftParen | RightParen) // relaxed def for "identifier or reserved word"
        (LeftParen argumentExpressionList? RightParen)?
    |   // empty
    ;

nestedParenthesesBlock
    :   (   ~(LeftParen | RightParen)
        |   LeftParen nestedParenthesesBlock RightParen
        )*
    ;

pointer
    :   Star typeQualifierList?
    |   Star typeQualifierList? pointer
    |   Caret typeQualifierList? // Blocks language extension
    |   Caret typeQualifierList? pointer // Blocks language extension
    ;

typeQualifierList
    :   typeQualifier
    |   typeQualifierList typeQualifier
    ;

parameterTypeList
    :   parameterList
    |   parameterList Comma Ellipsis
    ;

parameterList
    :   parameterDeclaration
    |   parameterList Comma parameterDeclaration
    ;

parameterDeclaration
    :   declarationSpecifiers declarator
    |   declarationSpecifiers2 abstractDeclarator?
    ;

identifierList
    :   Identifier
    |   identifierList Comma Identifier
    ;

typeName
    :   specifierQualifierList abstractDeclarator?
    ;

abstractDeclarator
    :   pointer
    |   pointer? directAbstractDeclarator gccDeclaratorExtension*
    ;

directAbstractDeclarator
    :   LeftParen abstractDeclarator RightParen gccDeclaratorExtension*
    |   LeftBracket typeQualifierList? assignmentExpression? RightBracket
    |   LeftBracket Static typeQualifierList? assignmentExpression RightBracket
    |   LeftBracket typeQualifierList Static assignmentExpression RightBracket
    |   LeftBracket Star RightBracket
    |   LeftParen parameterTypeList? RightParen gccDeclaratorExtension*
    |   directAbstractDeclarator LeftBracket typeQualifierList? assignmentExpression? RightBracket
    |   directAbstractDeclarator LeftBracket Static typeQualifierList? assignmentExpression RightBracket
    |   directAbstractDeclarator LeftBracket typeQualifierList Static assignmentExpression RightBracket
    |   directAbstractDeclarator LeftBracket Star RightBracket
    |   directAbstractDeclarator LeftParen parameterTypeList? RightParen gccDeclaratorExtension*
    ;

typedefName
    :   Identifier
    ;

initializer
    :   assignmentExpression
    |   LeftBrace initializerList RightBrace
    |   LeftBrace initializerList Comma RightBrace
    ;

initializerList
    :   designation? initializer
    |   initializerList Comma designation? initializer
    ;

designation
    :   designatorList Assign
    ;

designatorList
    :   designator
    |   designatorList designator
    ;

designator
    :   LeftBracket constantExpression RightBracket
    |   Dot Identifier
    ;

staticAssertDeclaration
    :   StaticAssert LeftParen constantExpression Comma StringLiteral+ RightParen Semi
    ;

statement
    :   labeledStatement
    |   compoundStatement
    |   expressionStatement
    |   selectionStatement
    |   iterationStatement
    |   jumpStatement
    |   ('__asm' | '__asm__') (Volatile | '__volatile__') LeftParen (logicalOrExpression (Comma logicalOrExpression)*)? (Colon (logicalOrExpression (Comma logicalOrExpression)*)?)* RightParen Semi
    ;

labeledStatement
    :   Identifier Colon statement
    |   Case constantExpression Colon statement
    |   Default Colon statement
    ;

compoundStatement
    :   LeftBrace blockItemList? RightBrace
    ;

blockItemList
    :   blockItem
    |   blockItemList blockItem
    ;

blockItem
    :   statement
    |   declaration
    ;

expressionStatement
    :   expression? Semi
    ;

selectionStatement
    :   If LeftParen expression RightParen statement (Else statement)?
    |   Switch LeftParen expression RightParen statement
    ;

iterationStatement
    :   While LeftParen expression RightParen statement
    |   Do statement While LeftParen expression RightParen Semi
    |   For LeftParen forCondition RightParen statement
    ;

//    |   'for' LeftParen expression? ';' expression?  ';' forUpdate? RightParen statement
//    |   For LeftParen declaration  expression? ';' expression? RightParen statement

forCondition
	:   forDeclaration Semi forExpression? Semi forExpression?
	|   expression? Semi forExpression? Semi forExpression?
	;

forDeclaration
    :   declarationSpecifiers initDeclaratorList
	| 	declarationSpecifiers
    ;

forExpression
    :   assignmentExpression
    |   forExpression Comma assignmentExpression
    ;

jumpStatement
    :   Goto Identifier Semi
    |   Continue Semi
    |   Break Semi
    |   Return expression? Semi
    |   Goto unaryExpression Semi // GCC extension
    ;

compilationUnit
    :   translationUnit? EOF
    ;

translationUnit
    :   externalDeclaration
    |   translationUnit externalDeclaration
    ;

externalDeclaration
    :   functionDefinition
    |   declaration
    |   Semi // stray ;
    ;

functionDefinition
    :   declarationSpecifiers? declarator declarationList? compoundStatement
    ;

declarationList
    :   declaration
    |   declarationList declaration
    ;


Auto : 'auto';
Break : 'break';
Case : 'case';
Char : 'char';
Const : 'const';
Continue : 'continue';
Default : 'default';
Do : 'do';
Double : 'double';
Else : 'else';
Enum : 'enum';
Extern : 'extern';
Float : 'float';
For : 'for';
Goto : 'goto';
If : 'if';
Inline : 'inline';
Int : 'int';
Long : 'long';
Register : 'register';
Restrict : 'restrict';
Return : 'return';
Short : 'short';
Signed : 'signed';
Sizeof : 'sizeof';
Static : 'static';
Struct : 'struct';
Switch : 'switch';
Typedef : 'typedef';
Union : 'union';
Unsigned : 'unsigned';
Void : 'void';
Volatile : 'volatile';
While : 'while';

Alignas : '_Alignas';
Alignof : '_Alignof';
Atomic : '_Atomic';
Bool : '_Bool';
Complex : '_Complex';
Generic : '_Generic';
Imaginary : '_Imaginary';
Noreturn : '_Noreturn';
StaticAssert : '_Static_assert';
ThreadLocal : '_Thread_local';

LeftParen : '(';
RightParen : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
LeftShift : '<<';
RightShift : '>>';

Plus : '+';
PlusPlus : '++';
Minus : '-';
MinusMinus : '--';
Star : '*';
Div : '/';
Mod : '%';

And : '&';
Or : '|';
AndAnd : '&&';
OrOr : '||';
Caret : '^';
Not : '!';
Tilde : '~';

Question : '?';
Colon : ':';
Semi : ';';
Comma : ',';

Assign : '=';
// '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
StarAssign : '*=';
DivAssign : '/=';
ModAssign : '%=';
PlusAssign : '+=';
MinusAssign : '-=';
LeftShiftAssign : '<<=';
RightShiftAssign : '>>=';
AndAssign : '&=';
XorAssign : '^=';
OrAssign : '|=';

Equal : '==';
NotEqual : '!=';

Arrow : '->';
Dot : '.';
Ellipsis : '...';

Identifier
    :   IdentifierNondigit
        (   IdentifierNondigit
        |   Digit
        )*
    ;

fragment
IdentifierNondigit
    :   Nondigit
    |   UniversalCharacterName
    //|   // other implementation-defined characters...
    ;

fragment
Nondigit
    :   [a-zA-Z_]
    ;

fragment
Digit
    :   [0-9]
    ;

fragment
UniversalCharacterName
    :   '\\u' HexQuad
    |   '\\U' HexQuad HexQuad
    ;

fragment
HexQuad
    :   HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
    ;

Constant
    :   IntegerConstant
    |   FloatingConstant
    //|   EnumerationConstant
    |   CharacterConstant
    ;

fragment
IntegerConstant
    :   DecimalConstant IntegerSuffix?
    |   OctalConstant IntegerSuffix?
    |   HexadecimalConstant IntegerSuffix?
    |	BinaryConstant
    ;

fragment
BinaryConstant
	:	'0' [bB] [0-1]+
	;

fragment
DecimalConstant
    :   NonzeroDigit Digit*
    ;

fragment
OctalConstant
    :   '0' OctalDigit*
    ;

fragment
HexadecimalConstant
    :   HexadecimalPrefix HexadecimalDigit+
    ;

fragment
HexadecimalPrefix
    :   '0' [xX]
    ;

fragment
NonzeroDigit
    :   [1-9]
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
HexadecimalDigit
    :   [0-9a-fA-F]
    ;

fragment
IntegerSuffix
    :   UnsignedSuffix LongSuffix?
    |   UnsignedSuffix LongLongSuffix
    |   LongSuffix UnsignedSuffix?
    |   LongLongSuffix UnsignedSuffix?
    ;

fragment
UnsignedSuffix
    :   [uU]
    ;

fragment
LongSuffix
    :   [lL]
    ;

fragment
LongLongSuffix
    :   'll' | 'LL'
    ;

fragment
FloatingConstant
    :   DecimalFloatingConstant
    |   HexadecimalFloatingConstant
    ;

fragment
DecimalFloatingConstant
    :   FractionalConstant ExponentPart? FloatingSuffix?
    |   DigitSequence ExponentPart FloatingSuffix?
    ;

fragment
HexadecimalFloatingConstant
    :   HexadecimalPrefix HexadecimalFractionalConstant BinaryExponentPart FloatingSuffix?
    |   HexadecimalPrefix HexadecimalDigitSequence BinaryExponentPart FloatingSuffix?
    ;

fragment
FractionalConstant
    :   DigitSequence? '.' DigitSequence
    |   DigitSequence '.'
    ;

fragment
ExponentPart
    :   'e' Sign? DigitSequence
    |   'E' Sign? DigitSequence
    ;

fragment
Sign
    :   '+' | '-'
    ;

DigitSequence
    :   Digit+
    ;

fragment
HexadecimalFractionalConstant
    :   HexadecimalDigitSequence? '.' HexadecimalDigitSequence
    |   HexadecimalDigitSequence '.'
    ;

fragment
BinaryExponentPart
    :   'p' Sign? DigitSequence
    |   'P' Sign? DigitSequence
    ;

fragment
HexadecimalDigitSequence
    :   HexadecimalDigit+
    ;

fragment
FloatingSuffix
    :   'f' | 'l' | 'F' | 'L'
    ;

fragment
CharacterConstant
    :   '\'' CCharSequence '\''
    |   'L\'' CCharSequence '\''
    |   'u\'' CCharSequence '\''
    |   'U\'' CCharSequence '\''
    ;

fragment
CCharSequence
    :   CChar+
    ;

fragment
CChar
    :   ~['\\\r\n]
    |   EscapeSequence
    ;
fragment
EscapeSequence
    :   SimpleEscapeSequence
    |   OctalEscapeSequence
    |   HexadecimalEscapeSequence
    |   UniversalCharacterName
    ;
fragment
SimpleEscapeSequence
    :   '\\' ['"?abfnrtv\\]
    ;
fragment
OctalEscapeSequence
    :   '\\' OctalDigit
    |   '\\' OctalDigit OctalDigit
    |   '\\' OctalDigit OctalDigit OctalDigit
    ;
fragment
HexadecimalEscapeSequence
    :   '\\x' HexadecimalDigit+
    ;
StringLiteral
    :   EncodingPrefix? '"' SCharSequence? '"'
    ;
fragment
EncodingPrefix
    :   'u8'
    |   'u'
    |   'U'
    |   'L'
    ;
fragment
SCharSequence
    :   SChar+
    ;
fragment
SChar
    :   ~["\\\r\n]
    |   EscapeSequence
    |   '\\\n'   // Added line
    |   '\\\r\n' // Added line
    ;

ComplexDefine
    :   '#' Whitespace? 'define'  ~[#]*
        -> skip
    ;

// ignore the following asm blocks:
/*
    asm
    {
        mfspr x, 286;
    }
 */
AsmBlock
    :   'asm' ~'{'* '{' ~'}'* '}'
	-> skip
    ;

// ignore the lines generated by j2c.c preprocessor
// sample line : '#line 1 "/home/dm/files/dk1.h" 1'
LineAfterPreprocessing
    :   '#line' Whitespace* ~[\r\n]*
        -> skip
    ;

LineDirective
    :   '#' Whitespace? DecimalConstant Whitespace? StringLiteral ~[\r\n]*
        -> skip
    ;

PragmaDirective
    :   '#' Whitespace? 'pragma' Whitespace ~[\r\n]*
        -> skip
    ;

Whitespace
    :   [ \t]+
        -> channel(HIDDEN)
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> channel(HIDDEN)
    ;

BlockComment
    :   '/*' .*? '*/'
        -> skip
    ;

LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;

SomePreprocessorStuff
     :   '#' ~[\r\n]*
          -> skip
     ;