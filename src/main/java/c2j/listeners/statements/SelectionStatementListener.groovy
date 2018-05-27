package c2j.listeners.statements

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait SelectionStatementListener extends BaseListenerTrait {
    @Override
    void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.If(), ctx.Switch(), ctx.LeftParen()])
    }
}