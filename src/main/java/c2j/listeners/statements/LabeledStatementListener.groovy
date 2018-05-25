package c2j.listeners.statements

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait LabeledStatementListener extends BaseListenerTrait {
    @Override
    void enterLabeledStatement(CParser.LabeledStatementContext ctx) {
        if (ctx.Identifier() != null) {
            appendIfNotNull ctx.Identifier().getText()
            appendIfNotNull ctx.Colon(), J.COLON
        } else if (ctx.Case() != null) {
            appendIfNotNull ctx.Case(), J.CASE
        } else if (ctx.Default() != null) {
            appendIfNotNull ctx.Default(), J.DEFAULT
            appendIfNotNull ctx.Colon(), J.COLON
        }
    }
}