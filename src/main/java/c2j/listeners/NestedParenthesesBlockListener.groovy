package c2j.listeners

import c2j.c.CParser

trait NestedParenthesesBlockListener extends BaseListenerTrait {
    @Override
    void enterNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {
        ctx.LeftParen()?.forEach { lparen -> appendIfNotNull lparen }
    }

    @Override
    void exitNestedParenthesesBlock(CParser.NestedParenthesesBlockContext ctx) {
        ctx.RightParen()?.forEach { rparen -> appendIfNotNull rparen }
    }
}