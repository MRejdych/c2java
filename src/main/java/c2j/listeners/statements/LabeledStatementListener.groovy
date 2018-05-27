package c2j.listeners.statements

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait LabeledStatementListener extends BaseListenerTrait {
    @Override
    void enterLabeledStatement(CParser.LabeledStatementContext ctx) {
        if (ctx.Identifier() != null) {
            appendIfNotNull ctx.Identifier().getText()
            translateAndAppendIfNotNull([ctx.Colon()], ctx)
        } else if (ctx.Case() != null) {
            translateAndAppendIfNotNull([ctx.Case()], ctx)
        } else if (ctx.Default() != null) {
            translateAndAppendIfNotNull([ctx.Default(), ctx.Colon()], ctx)
        }
    }
}