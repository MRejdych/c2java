package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait LogicalOrExpressionListener extends BaseListenerTrait {
    @Override
    void enterLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {

    }

    @Override
    void exitLogicalOrExpression(CParser.LogicalOrExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.ConditionalExpressionContext) {
            def parent = ctx.getParent() as CParser.ConditionalExpressionContext

            appendIfNotNull parent.Question(), J.QUESTION


        }
    }
}