package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait ForExpressionListener extends BaseListenerTrait {
    @Override
    void enterForExpression(CParser.ForExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.ForConditionContext) {
            def parent = ctx.getParent() as CParser.ForConditionContext
            translateAndAppendIfNotNull([parent.Semi()[0]], parent)
        }
    }
}
