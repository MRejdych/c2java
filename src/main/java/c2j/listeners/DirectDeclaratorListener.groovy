package c2j.listeners

import c2j.J
import c2j.c.CParser

trait DirectDeclaratorListener extends BaseListenerTrait {
    @Override
    void enterDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        appendHiddenTokensToLeftOf ctx
        appendIfNotNull ctx.Identifier()
        if (ctx.declarator() != null) {
            appendIfNotNull ctx.LeftParen(), J.LPAREN
        }
    }

    @Override
    void exitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        if (ctx.getParent() instanceof CParser.DirectDeclaratorContext) {
            def parent = ctx.getParent() as CParser.DirectDeclaratorContext
            if (parent.pointer() == null) {
                appendIfNotNull parent.LeftParen(), J.LPAREN
                appendIfNotNull parent.LeftBracket(), J.LBRACK
            }
        }
        appendIfNotNull ctx.RightParen(), J.RPAREN
        appendIfNotNull ctx.RightBracket(), J.RBRACK
    }
}