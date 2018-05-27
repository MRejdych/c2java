package c2j.listeners.statements

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait JumpStatementListener extends BaseListenerTrait {
    @Override
    void enterJumpStatement(CParser.JumpStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.Return(), ctx.Continue(), ctx.Break()])
        if (ctx.Goto() != null) appendIfNotNull "//TODO implement alternative to C goto jump instruction\n"
    }

    @Override
    void exitJumpStatement(CParser.JumpStatementContext ctx) {
        appendIfNotNull ";"
    }
}