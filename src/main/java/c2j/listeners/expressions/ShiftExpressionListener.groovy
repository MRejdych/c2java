package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ShiftExpressionListener extends BaseListenerTrait {
    @Override
    void enterShiftExpression(CParser.ShiftExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.RelationalExpressionContext) {
            def parent = ctx.getParent() as CParser.RelationalExpressionContext
            appendIfNotNull parent.Less(), J.LT
            appendIfNotNull parent.LessEqual(), J.LE
            appendIfNotNull parent.Greater(), J.GT
            appendIfNotNull parent.GreaterEqual(), J.GE
        }
    }

    @Override
    void exitShiftExpression(CParser.ShiftExpressionContext ctx) {

    }
}