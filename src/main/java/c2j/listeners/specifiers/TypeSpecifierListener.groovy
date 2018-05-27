package c2j.listeners.specifiers

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait TypeSpecifierListener extends BaseListenerTrait {
    @Override
    void enterTypeSpecifier(CParser.TypeSpecifierContext ctx) {
        translateAndAppendIfNotNull(
                [ctx.Char(), ctx.Short(), ctx.Int(), ctx.Long(), ctx.Float(), ctx.Double(), ctx.Bool(), ctx.Signed(), ctx.Unsigned(), ctx.Complex()]
        )
    }
}