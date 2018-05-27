package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ExclusiveOrExpressionListener extends BaseListenerTrait {
    @Override
    void enterExclusiveOrExpression(CParser.ExclusiveOrExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.InclusiveOrExpressionContext) {
            def parent = ctx.getParent() as CParser.InclusiveOrExpressionContext
            translateAndAppendIfNotNull([parent.Or()])
        }
    }
}
