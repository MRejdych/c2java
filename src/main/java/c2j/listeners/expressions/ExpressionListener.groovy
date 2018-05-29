package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait ExpressionListener extends BaseListenerTrait {
    @Override
    void enterExpression(CParser.ExpressionContext ctx) {
        if(ctx.parent instanceof CParser.PostfixExpressionContext) {
            def parent = ctx.parent as CParser.PostfixExpressionContext
            translateAndAppendIfNotNull([parent.LeftBracket()], ctx)
        }
    }

    @Override
    void exitExpression(CParser.ExpressionContext ctx) {
        def genericParent = ctx.getParent()
        if (genericParent instanceof CParser.ConditionalExpressionContext) {
            def parent = genericParent as CParser.ConditionalExpressionContext
            translateAndAppendIfNotNull([parent.Colon()], parent)
        } else if (genericParent instanceof CParser.SelectionStatementContext) {
            def parent = genericParent as CParser.SelectionStatementContext
            translateAndAppendIfNotNull([parent.RightParen()], parent)
        } else if(genericParent instanceof CParser.PostfixExpressionContext) {
            def parent = genericParent as CParser.PostfixExpressionContext
            translateAndAppendIfNotNull([parent.RightBracket()], ctx)
        }
    }
}