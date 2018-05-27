package c2j.listeners.expressions

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait CastExpressionListener extends BaseListenerTrait {
    @Override
    void enterCastExpression(CParser.CastExpressionContext ctx) {
        if (ctx.getParent() instanceof CParser.MultiplicativeExpressionContext) {
            def parent = ctx.getParent() as CParser.MultiplicativeExpressionContext
            translateAndAppendIfNotNull([parent.Star(), parent.Div(), parent.Mod()], parent)
        }
        translateAndAppendIfNotNull([ctx.LeftParen()], ctx)
        appendIfNotNull ctx.DigitSequence()?.getText()
    }

    @Override
    void exitCastExpression(CParser.CastExpressionContext ctx) {
        translateAndAppendIfNotNull([ctx.RightParen()], ctx)
    }
}