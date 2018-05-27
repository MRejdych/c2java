package c2j.listeners.statements

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait CompoundStatementListener extends BaseListenerTrait {
    @Override
    void enterCompoundStatement(CParser.CompoundStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.LeftBrace()])
    }

    @Override
    void exitCompoundStatement(CParser.CompoundStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.RightBrace()])
    }
}