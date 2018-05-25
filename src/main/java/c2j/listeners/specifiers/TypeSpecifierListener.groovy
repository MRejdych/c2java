package c2j.listeners.specifiers

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait TypeSpecifierListener extends BaseListenerTrait {
    @Override
    void enterTypeSpecifier(CParser.TypeSpecifierContext ctx) {

        appendIfNotNull ctx.Char(), J.CHAR
        appendIfNotNull ctx.Short(), J.SHORT
        appendIfNotNull ctx.Int(), J.INT
        appendIfNotNull ctx.Long(), J.LONG
        appendIfNotNull ctx.Float(), J.FLOAT
        appendIfNotNull ctx.Double(), J.DOUBLE
        appendIfNotNull ctx.Bool(), J.BOOLEAN
    }

    @Override
    void exitTypeSpecifier(CParser.TypeSpecifierContext ctx) {

    }
}