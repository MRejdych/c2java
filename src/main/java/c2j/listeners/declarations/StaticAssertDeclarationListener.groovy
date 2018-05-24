package c2j.listeners.declarations

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait StaticAssertDeclarationListener extends BaseListenerTrait {
    @Override
    void enterStaticAssertDeclaration(CParser.StaticAssertDeclarationContext ctx) {
        appendIfNotNull ctx.StaticAssert(), J.ASSERT
        appendIfNotNull ctx.LeftParen(), J.LPAREN
    }

    @Override
    void exitStaticAssertDeclaration(CParser.StaticAssertDeclarationContext ctx) {
        appendIfNotNull ctx.Comma(), J.COMMA
        ctx.StringLiteral()?.forEach { literal -> appendIfNotNull literal }
        appendIfNotNull ctx.RightParen(), J.RPAREN
        appendIfNotNull ctx.Semi(), J.SEMI
    }
}
