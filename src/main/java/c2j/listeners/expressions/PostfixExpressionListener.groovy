package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait PostfixExpressionListener extends BaseListenerTrait {
    @Override
    void exitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        translateAndAppendIfNotNull([ctx.PlusPlus(), ctx.MinusMinus(), ctx.Dot()], ctx)
        appendIfNotNull ctx.Identifier()
        translateAndAppendIfNotNull([ctx.RightParen()], ctx)

    }
}