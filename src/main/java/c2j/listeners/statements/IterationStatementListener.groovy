package c2j.listeners.statements

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait IterationStatementListener extends BaseListenerTrait {
    @Override
    void enterIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.Do() != null) {
            translateAndAppendIfNotNull([ctx.Do()])
            return
        }
        translateAndAppendIfNotNull([ctx.While(), ctx.For(), ctx.LeftParen()])
    }

    @Override
    void exitIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.Do() != null) {
            translateAndAppendIfNotNull([ctx.LeftParen(), ctx.Semi()])
        }
    }
}