package c2j.listeners

import c2j.antlrGenerated.CParser

trait DesignatorListener extends BaseListenerTrait{
    @Override
    void enterDesignator(CParser.DesignatorContext ctx) {
        translateAndAppendIfNotNull([ctx.LeftBracket()], ctx)
    }

    @Override
    void exitDesignator(CParser.DesignatorContext ctx) {
        translateAndAppendIfNotNull([ctx.RightBracket()], ctx)
    }
}