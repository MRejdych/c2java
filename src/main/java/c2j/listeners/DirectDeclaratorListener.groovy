package c2j.listeners

import c2j.antlrGenerated.CParser

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
        } else if (ctx.getParent() instanceof CParser.DirectDeclaratorContext) {
            def parent = ctx.getParent() as CParser.DirectDeclaratorContext
            if (parent.LeftBracket() && parent.assignmentExpression() && parent.RightBracket()) {
                int firstWhiteSpaceIndex = getTokenChannel().getHiddenTokensToLeft(parent.start.tokenIndex)
                        .stream()
                        .sorted({ x, y -> x.tokenIndex <=> y.tokenIndex })
                        .findFirst()
                        .get()
                        ?.tokenIndex

                appendIfNotNull "[] = new ${getTokenChannel().get(firstWhiteSpaceIndex - 1)?.getText()}"
            }
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