package c2j.listeners.expressions

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait PrimaryExpressionListener extends BaseListenerTrait {

    @Override
    void enterPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        appendHiddenTokensToLeftOf ctx
        appendIfNotNull ctx.Identifier()
        appendIfNotNull ctx.Constant()
        ctx.StringLiteral().forEach({ literal -> appendIfNotNull literal })
        appendIfNotNull ctx.LeftParen()
    }

    @Override
    void exitPrimaryExpression(CParser.PrimaryExpressionContext ctx) {
        appendIfNotNull ctx.RightParen()
    }
}