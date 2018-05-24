package c2j.listeners.declarations

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ExternalDeclarationListener extends BaseListenerTrait {
    @Override
    void enterExternalDeclaration(CParser.ExternalDeclarationContext ctx) {

    }

    @Override
    void exitExternalDeclaration(CParser.ExternalDeclarationContext ctx) {
        appendIfNotNull ctx.Semi(), J.SEMI
    }
}