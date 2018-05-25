package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ConstantExpressionListener extends BaseListenerTrait {
    @Override
    void exitConstantExpression(CParser.ConstantExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.LabeledStatementContext) {
            def parent = ctx.parent as CParser.LabeledStatementContext
            appendIfNotNull parent.Colon(), J.COLON
        }
    }
}