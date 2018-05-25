package c2j.listeners.lists

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ArgumentExpressionListListener extends BaseListenerTrait {

    @Override
    void enterArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {
        if (ctx.parent instanceof CParser.PostfixExpressionContext) {
            def parent = ctx.parent as CParser.PostfixExpressionContext
            appendIfNotNull parent.LeftParen(), J.LPAREN
        }
    }

    @Override
    void exitArgumentExpressionList(CParser.ArgumentExpressionListContext ctx) {
        if (ctx.getParent() instanceof CParser.ArgumentExpressionListContext) {
            def parent = ctx.getParent() as CParser.ArgumentExpressionListContext
            appendIfNotNull parent.Comma(), J.COMMA
        }
    }
}