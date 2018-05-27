package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait PostfixExpressionListener extends BaseListenerTrait {
    @Override
    void exitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        translateAndAppendIfNotNull([ctx.PlusPlus(), ctx.MinusMinus(), ctx.Dot()])
        appendIfNotNull ctx.Identifier()
        translateAndAppendIfNotNull([ctx.RightParen()])

    }
}