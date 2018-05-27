package c2j.listeners.statements

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ExpressionStatementListener extends BaseListenerTrait {
    @Override
    void exitExpressionStatement(CParser.ExpressionStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.Semi()], ctx)
    }
}