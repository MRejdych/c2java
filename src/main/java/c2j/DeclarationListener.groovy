package c2j

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait DeclarationListener extends BaseListenerTrait {
    @Override
    void enterDeclaration(CParser.DeclarationContext ctx) {

    }

    @Override
    void exitDeclaration(CParser.DeclarationContext ctx) {
        appendIfNotNull ctx.Semi(), J.SEMI
    }
}