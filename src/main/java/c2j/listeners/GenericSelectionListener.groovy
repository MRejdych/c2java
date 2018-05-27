package c2j.listeners

import c2j.antlrGenerated.CParser

trait GenericSelectionListener extends BaseListenerTrait {
    @Override
    void enterGenericSelection(CParser.GenericSelectionContext ctx) {
        translateAndAppendIfNotNull([ctx.LeftParen()], ctx)
    }

    @Override
    void exitGenericSelection(CParser.GenericSelectionContext ctx) {
        translateAndAppendIfNotNull([ctx.RightParen()], ctx)
    }
}
