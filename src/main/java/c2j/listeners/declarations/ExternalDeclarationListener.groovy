package c2j.listeners.declarations

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ExternalDeclarationListener extends BaseListenerTrait {
    @Override
    void exitExternalDeclaration(CParser.ExternalDeclarationContext ctx) {
        translateAndAppendIfNotNull([ctx.Semi()], ctx)
    }
}