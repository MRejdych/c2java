package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait


trait RelationalExpressionListener extends BaseListenerTrait {
    @Override
    void enterRelationalExpression(CParser.RelationalExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.EqualityExpressionContext) {
            def parent = ctx.getParent() as CParser.EqualityExpressionContext
            appendIfNotNull parent.Equal(), J.EQUAL
            appendIfNotNull parent.NotEqual(), J.NOTEQUAL
        }
    }
}