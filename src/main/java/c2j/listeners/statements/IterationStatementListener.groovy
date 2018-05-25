package c2j.listeners.statements

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait IterationStatementListener extends BaseListenerTrait {
    @Override
    void enterIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.Do() != null) {
            appendIfNotNull ctx.Do(), J.DO
            return
        }
        appendIfNotNull ctx.While(), J.WHILE
        appendIfNotNull ctx.For(), J.FOR
        appendIfNotNull ctx.LeftParen(), J.LPAREN
    }

    @Override
    void exitIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.Do() != null) {
            appendIfNotNull ctx.LeftParen(), J.LPAREN
            appendIfNotNull ctx.Semi(), J.SEMI
        }
    }
}