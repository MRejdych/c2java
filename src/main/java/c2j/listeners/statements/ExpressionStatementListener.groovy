package c2j.listeners.statements

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ExpressionStatementListener extends BaseListenerTrait {
    @Override
    void enterExpressionStatement(CParser.ExpressionStatementContext ctx) {

    }

    @Override
    void exitExpressionStatement(CParser.ExpressionStatementContext ctx) {
        appendIfNotNull ctx.Semi(), J.SEMI
    }
}