package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait ConstantExpressionListener extends BaseListenerTrait {
    @Override
    void exitConstantExpression(CParser.ConstantExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.LabeledStatementContext) {
            def parent = ctx.parent as CParser.LabeledStatementContext
            translateAndAppendIfNotNull([parent.Colon()], parent)
        }
    }
}