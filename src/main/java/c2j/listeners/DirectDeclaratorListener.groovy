package c2j.listeners

import c2j.c.CParser

trait DirectDeclaratorListener extends BaseListenerTrait {
    @Override
    void enterDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        appendIfNotNull ctx.Identifier()
        if (ctx.declarator() != null) {
            translateAndAppendIfNotNull([ctx.LeftParen()], ctx)
        }
        Optional<String> className = Optional.ofNullable(getClassNameIfPreceding())
        if (className.isPresent()) {
            appendIfNotNull " = new ${className.get()}()"
        }

    }

    @Override
    void exitDirectDeclarator(CParser.DirectDeclaratorContext ctx) {
        if (ctx.getParent() instanceof CParser.DirectDeclaratorContext) {
            def parent = ctx.getParent() as CParser.DirectDeclaratorContext
            if (parent.pointer() == null) {
                translateAndAppendIfNotNull([parent.LeftParen(), parent.LeftBracket()], parent)
            }
        }
        translateAndAppendIfNotNull([ctx.RightParen(), ctx.RightBracket()], ctx)
    }

}