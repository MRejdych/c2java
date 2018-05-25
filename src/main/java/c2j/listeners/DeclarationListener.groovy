package c2j.listeners

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait DeclarationListener extends BaseListenerTrait {
    @Override
    void exitDeclaration(CParser.DeclarationContext ctx) {
        appendIfNotNull ctx.Semi(), J.SEMI
    }
}