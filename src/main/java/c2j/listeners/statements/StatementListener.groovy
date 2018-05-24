package c2j.listeners.statements

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait StatementListener extends BaseListenerTrait {
    @Override
    void enterStatement(CParser.StatementContext ctx) {
        appendHiddenTokensToLeftOf ctx

        def genericParent = ctx.getParent()

        if (genericParent instanceof CParser.SelectionStatementContext) {
            def parent = genericParent as CParser.SelectionStatementContext
            if (parent.If() != null && parent.statement().indexOf(ctx) != 0) {
                appendIfNotNull parent.Else(), J.ELSE
            }
        } else if (genericParent instanceof CParser.IterationStatementContext) {
            def parent = genericParent as CParser.IterationStatementContext
            if (parent.Do() == null) {
                appendIfNotNull parent.RightParen(), J.RPAREN
            }
        }
    }

    @Override
    void exitStatement(CParser.StatementContext ctx) {
        def genericParent = ctx.getParent()

        if (genericParent instanceof CParser.IterationStatementContext) {
            def parent = genericParent as CParser.IterationStatementContext
            if (parent.Do() != null) {
                appendIfNotNull parent.While(), J.WHILE
                appendIfNotNull parent.RightParen(), J.RPAREN
            }
        }
    }
}