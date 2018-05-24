package c2j.listeners.statements

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait CompoundStatementListener extends BaseListenerTrait {
    @Override
    void enterCompoundStatement(CParser.CompoundStatementContext ctx) {
        appendIfNotNull ctx.LeftBrace(), J.LBRACE
    }

    @Override
    void exitCompoundStatement(CParser.CompoundStatementContext ctx) {
        appendIfNotNull ctx.RightBrace(), J.RBRACE
    }
}