package c2j.listeners.lists

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait IdentifierListListener extends BaseListenerTrait {
    @Override
    void exitIdentifierList(CParser.IdentifierListContext ctx) {
        appendIfNotNull ctx.Comma(), J.COMMA
        appendIfNotNull ctx.Identifier()?.getText()
    }
}