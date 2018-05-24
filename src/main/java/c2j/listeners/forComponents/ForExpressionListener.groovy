package c2j.listeners.forComponents

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ForExpressionListener extends BaseListenerTrait {
    @Override
    void enterForExpression(CParser.ForExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.ForConditionContext) {
            def parent = ctx.getParent() as CParser.ForConditionContext
            appendIfNotNull parent.Semi()[0], J.SEMI
        }
    }

    @Override
    void exitForExpression(CParser.ForExpressionContext ctx) {

    }
}
