package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait AndExpressionListener extends BaseListenerTrait {
    @Override
    void enterAndExpression(CParser.AndExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.ExclusiveOrExpressionContext) {
            def parent = ctx.getParent() as CParser.ExclusiveOrExpressionContext
            translateAndAppendIfNotNull([parent.Caret()], parent)
        }
    }
}
