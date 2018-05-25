package c2j.listeners

import c2j.J
import c2j.c.CParser

trait InitializerListener extends BaseListenerTrait {
    @Override
    void enterInitializer(CParser.InitializerContext ctx) {
        if (ctx.parent instanceof CParser.InitDeclaratorContext) {
            def parent = ctx.parent as CParser.InitDeclaratorContext
            appendIfNotNull parent.Assign(), J.ASSIGN
            appendIfNotNull " "
        }
    }
}