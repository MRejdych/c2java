package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait InclusiveOrExpressionListener extends BaseListenerTrait {
    @Override
    void enterInclusiveOrExpression(CParser.InclusiveOrExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.LogicalAndExpressionContext) {
            def parent = ctx.getParent() as CParser.LogicalAndExpressionContext
            translateAndAppendIfNotNull([parent.AndAnd()], parent)
        }
    }
}