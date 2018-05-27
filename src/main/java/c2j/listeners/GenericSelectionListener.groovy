package c2j.listeners

import c2j.c.CParser

trait GenericSelectionListener extends BaseListenerTrait {
    @Override
    void enterGenericSelection(CParser.GenericSelectionContext ctx) {
        translateAndAppendIfNotNull([ctx.LeftParen()])
    }

    @Override
    void exitGenericSelection(CParser.GenericSelectionContext ctx) {
        translateAndAppendIfNotNull([ctx.RightParen()])
    }
}
