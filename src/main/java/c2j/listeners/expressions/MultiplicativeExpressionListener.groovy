package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait MultiplicativeExpressionListener extends BaseListenerTrait {
    @Override
    void enterMultiplicativeExpression(CParser.MultiplicativeExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.AdditiveExpressionContext) {
            def parent = ctx.getParent() as CParser.AdditiveExpressionContext
            translateAndAppendIfNotNull([parent.Plus(), parent.Minus()], parent)
        }
    }
}
