package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ExpressionListener extends BaseListenerTrait {
    @Override
    void exitExpression(CParser.ExpressionContext ctx) {
        def genericParent = ctx.getParent()
        if (genericParent instanceof CParser.ConditionalExpressionContext) {
            def parent = genericParent as CParser.ConditionalExpressionContext
            translateAndAppendIfNotNull([parent.Colon()], parent)
        } else if (genericParent instanceof CParser.SelectionStatementContext) {
            def parent = genericParent as CParser.SelectionStatementContext
            translateAndAppendIfNotNull([parent.RightParen()], parent)
        }
    }
}