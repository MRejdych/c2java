package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ShiftExpressionListener extends BaseListenerTrait {
    @Override
    void enterShiftExpression(CParser.ShiftExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.RelationalExpressionContext) {
            def parent = ctx.getParent() as CParser.RelationalExpressionContext
            translateAndAppendIfNotNull([parent.Less(), parent.LessEqual(), parent.Greater(), parent.Greater()])
        }
    }
}