package c2j.listeners.statements

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait SelectionStatementListener extends BaseListenerTrait {
    @Override
    void enterSelectionStatement(CParser.SelectionStatementContext ctx) {
        appendIfNotNull ctx.If(), J.IF
        appendIfNotNull ctx.Switch(), J.SWITCH
        appendIfNotNull ctx.LeftParen(), J.LPAREN
    }
}