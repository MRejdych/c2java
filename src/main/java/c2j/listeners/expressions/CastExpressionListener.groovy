package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait CastExpressionListener extends BaseListenerTrait {
    @Override
    void enterCastExpression(CParser.CastExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.MultiplicativeExpressionContext) {
            def parent = ctx.getParent() as CParser.MultiplicativeExpressionContext
            translateAndAppendIfNotNull([parent.Star(), parent.Div(), parent.Mod()])
        }
        translateAndAppendIfNotNull([ctx.LeftParen()])
        appendIfNotNull ctx.DigitSequence()?.getText()
    }

    @Override
    void exitCastExpression(CParser.CastExpressionContext ctx) {
        translateAndAppendIfNotNull([ctx.RightParen()])
    }
}