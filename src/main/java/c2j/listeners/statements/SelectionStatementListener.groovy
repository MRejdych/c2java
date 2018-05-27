package c2j.listeners.statements

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait SelectionStatementListener extends BaseListenerTrait {
    @Override
    void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        translateAndAppendIfNotNull([ctx.If(), ctx.Switch(), ctx.LeftParen()], ctx)
    }
}