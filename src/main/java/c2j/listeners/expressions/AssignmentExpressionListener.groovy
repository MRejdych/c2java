package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait AssignmentExpressionListener extends BaseListenerTrait {
    @Override
    void enterAssignmentExpression(CParser.AssignmentExpressionContext ctx) {
        appendIfNotNull ctx.DigitSequence()?.getText()

        if (ctx.getParent() instanceof CParser.ExpressionContext) {
            def parent = ctx.getParent() as CParser.ExpressionContext
            appendIfNotNull parent.Comma(), J.COMMA
        } else if (ctx.getParent() instanceof CParser.ForExpressionContext) {
            def parent = ctx.getParent() as CParser.ForExpressionContext
            appendIfNotNull parent.Comma(), J.COMMA
        }
    }

    @Override
    void exitAssignmentExpression(CParser.AssignmentExpressionContext ctx) {

    }
}
