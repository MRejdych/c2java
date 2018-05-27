package c2j.listeners

import c2j.antlrGenerated.CParser

trait DeclarationListener extends BaseListenerTrait {
    @Override
    void exitDeclaration(CParser.DeclarationContext ctx) {
        translateAndAppendIfNotNull([ctx.Semi()], ctx)
    }
}