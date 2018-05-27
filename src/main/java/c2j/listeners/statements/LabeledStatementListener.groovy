package c2j.listeners.statements

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait LabeledStatementListener extends BaseListenerTrait {
    @Override
    void enterLabeledStatement(CParser.LabeledStatementContext ctx) {
        if (ctx.Identifier() != null) {
            appendIfNotNull ctx.Identifier().getText()
            translateAndAppendIfNotNull([ctx.Colon()])
        } else if (ctx.Case() != null) {
            translateAndAppendIfNotNull([ctx.Case()])
        } else if (ctx.Default() != null) {
            translateAndAppendIfNotNull([ctx.Default(), ctx.Colon()])
        }
    }
}