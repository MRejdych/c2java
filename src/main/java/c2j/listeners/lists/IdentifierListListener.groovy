package c2j.listeners.lists

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait IdentifierListListener extends BaseListenerTrait {
    @Override
    void exitIdentifierList(CParser.IdentifierListContext ctx) {
        translateAndAppendIfNotNull([ctx.Comma()], ctx)
        appendIfNotNull ctx.Identifier()?.getText()
    }
}