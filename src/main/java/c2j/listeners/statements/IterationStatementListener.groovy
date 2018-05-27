package c2j.listeners.statements

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait IterationStatementListener extends BaseListenerTrait {
    @Override
    void enterIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.Do() != null) {
            translateAndAppendIfNotNull([ctx.Do()], ctx)
            return
        }
        translateAndAppendIfNotNull([ctx.While(), ctx.For(), ctx.LeftParen()], ctx)
    }

    @Override
    void exitIterationStatement(CParser.IterationStatementContext ctx) {
        if (ctx.Do() != null) {
            translateAndAppendIfNotNull([ctx.LeftParen(), ctx.Semi()], ctx)
        }
    }
}