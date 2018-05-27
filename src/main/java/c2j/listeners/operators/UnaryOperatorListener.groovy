package c2j.listeners.operators

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait UnaryOperatorListener extends BaseListenerTrait {
    @Override
    void enterUnaryOperator(CParser.UnaryOperatorContext ctx) {
        translateAndAppendIfNotNull([ctx.And(), ctx.Not(), ctx.Plus(), ctx.Minus(), ctx.Tilde(), ctx.Star()])
    }
}