package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait EqualityExpressionListener extends BaseListenerTrait {
    @Override
    void enterEqualityExpression(CParser.EqualityExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.AndExpressionContext) {
            def parent = ctx.getParent() as CParser.AndExpressionContext
            translateAndAppendIfNotNull([parent.And()], parent)
        }
    }
}
