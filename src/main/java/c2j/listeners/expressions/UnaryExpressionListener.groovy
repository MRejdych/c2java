package c2j.listeners.expressions

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait UnaryExpressionListener extends BaseListenerTrait {
    @Override
    void enterUnaryExpression(CParser.UnaryExpressionContext ctx) {
        appendIfNotNull ctx.PlusPlus(), J.INC
        appendIfNotNull ctx.MinusMinus(), J.DEC
    }
}