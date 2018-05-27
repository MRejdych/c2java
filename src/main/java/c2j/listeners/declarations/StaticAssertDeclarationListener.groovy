package c2j.listeners.declarations

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait StaticAssertDeclarationListener extends BaseListenerTrait {
    @Override
    void enterStaticAssertDeclaration(CParser.StaticAssertDeclarationContext ctx) {
        translateAndAppendIfNotNull([ctx.StaticAssert(), ctx.LeftParen()])
    }

    @Override
    void exitStaticAssertDeclaration(CParser.StaticAssertDeclarationContext ctx) {
        translateAndAppendIfNotNull([ctx.Comma()])
        ctx.StringLiteral()?.forEach { literal -> appendIfNotNull literal }
        translateAndAppendIfNotNull([ctx.RightParen(), ctx.Semi()])
    }
}
