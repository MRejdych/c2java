package c2j.listeners

import c2j.J
import c2j.c.CParser
import org.antlr.v4.runtime.ParserRuleContext

trait DirectDeclaratorListener extends BaseListenerTrait {
    @Override
    void enterDirectDeclarator(CParser.DirectDeclaratorContext ctx) {

        appendIfNotNull ctx.Identifier()
        if (ctx.declarator() != null) {
            appendIfNotNull ctx.LeftParen(), J.LPAREN
        }
        Optional<String> className = Optional.ofNullable(getClassNameIfPreceeding())
        if (className.isPresent()) {
            appendIfNotNull " = new ${className.get()}()"
        }

    }

    @Override
    void exitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {

        if (ctx.getParent() instanceof CParser.DirectDeclaratorContext) {
            def parent = ctx.getParent() as CParser.DirectDeclaratorContext
            if (parent.pointer() == null) {
                appendIfNotNull parent.LeftParen(), J.LPAREN
                appendIfNotNull parent.LeftBracket(), J.LBRACK
            }
        }
        appendIfNotNull ctx.RightParen(), J.RPAREN
        appendIfNotNull ctx.RightBracket(), J.RBRACK

    }

    boolean shouldBeProceeded(ParserRuleContext ctx) {
        if (ctx?.parent instanceof CParser.DirectDeclaratorContext) {
            return (ctx.parent as CParser.DirectDeclaratorContext)?.parameterTypeList() == null
        } else return true
    }
}