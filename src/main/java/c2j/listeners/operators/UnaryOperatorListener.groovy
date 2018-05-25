package c2j.listeners.operators

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait UnaryOperatorListener extends BaseListenerTrait {
    @Override
    void enterUnaryOperator(CParser.UnaryOperatorContext ctx) {

        appendIfNotNull ctx.And(), J.BITAND
        appendIfNotNull ctx.Not(), J.BANG
        appendIfNotNull ctx.Plus(), J.ADD
        appendIfNotNull ctx.Minus(), J.SUB
        appendIfNotNull ctx.Tilde(), J.TILDE
        appendIfNotNull ctx.Star(), J.MUL
    }

    @Override
    void exitUnaryOperator(CParser.UnaryOperatorContext ctx) {

    }
}