package c2j.listeners

import c2j.J
import c2j.c.CParser

trait GenericSelectionListener extends BaseListenerTrait {
    @Override
    void enterGenericSelection(CParser.GenericSelectionContext ctx) {
        appendIfNotNull ctx.LeftParen(), J.LPAREN
    }

    @Override
    void exitGenericSelection(CParser.GenericSelectionContext ctx) {
        appendIfNotNull ctx.RightParen(), J.RPAREN
    }
}
