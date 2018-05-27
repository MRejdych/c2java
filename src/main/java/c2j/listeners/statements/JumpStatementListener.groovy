package c2j.listeners.statements

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait JumpStatementListener extends BaseListenerTrait {
    @Override
    void enterJumpStatement(CParser.JumpStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.Return(), ctx.Continue(), ctx.Break(), ctx.Goto()], ctx)
    }

    @Override
    void exitJumpStatement(CParser.JumpStatementContext ctx) {
        if (ctx.Goto() == null) {
            translateAndAppendIfNotNull([ctx.Semi()], ctx)
        }
    }
}