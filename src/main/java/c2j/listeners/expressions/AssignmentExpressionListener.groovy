package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait AssignmentExpressionListener extends BaseListenerTrait {
    @Override
    void enterAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
        appendIfNotNull ctx.DigitSequence()?.getText()

        if (ctx.getParent() instanceof CParser.ExpressionContext) {
            def parent = ctx.getParent() as CParser.ExpressionContext
            translateAndAppendIfNotNull([parent.Comma()], parent)
        } else if (ctx.getParent() instanceof CParser.ForExpressionContext) {
            def parent = ctx.getParent() as CParser.ForExpressionContext
            translateAndAppendIfNotNull([parent.Comma()], parent)
        }
    }
}
