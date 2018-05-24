package c2j.listeners

import c2j.c.CParser

trait TypedefNameListener extends BaseListenerTrait {
    @Override
    void enterTypedefName(CParser.TypedefNameContext ctx) {
        appendIfNotNull ctx.Identifier()
    }

    @Override
    void exitTypedefName(CParser.TypedefNameContext ctx) {
        appendIfNotNull ";"
    }
}