package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait


trait PostfixExpressionListener extends BaseListenerTrait {
    @Override
    void enterPostfixExpression(CParser.PostfixExpressionContext ctx) {

    }

    @Override
    void exitPostfixExpression(CParser.PostfixExpressionContext ctx) {
        appendIfNotNull ctx.PlusPlus(), J.INC
        appendIfNotNull ctx.MinusMinus(), J.DEC
        appendIfNotNull ctx.Dot(), J.DOT
        appendIfNotNull ctx.Identifier()
    }
}