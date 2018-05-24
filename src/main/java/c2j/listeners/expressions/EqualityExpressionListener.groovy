package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait EqualityExpressionListener extends BaseListenerTrait {
    @Override
    void enterEqualityExpression(CParser.EqualityExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.AndExpressionContext) {
            def parent = ctx.getParent() as CParser.AndExpressionContext
            appendIfNotNull parent.And(), J.BITAND
        }
    }

    @Override
    void exitEqualityExpression(CParser.EqualityExpressionContext ctx) {

    }
}
