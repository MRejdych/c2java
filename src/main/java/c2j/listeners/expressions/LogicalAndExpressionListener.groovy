package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait LogicalAndExpressionListener extends BaseListenerTrait {
    @Override
    void enterLogicalAndExpression(CParser.LogicalAndExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.LogicalOrExpressionContext) {
            def parent = ctx.getParent() as CParser.LogicalOrExpressionContext
            translateAndAppendIfNotNull([parent.OrOr()], parent)
        }
    }
}
