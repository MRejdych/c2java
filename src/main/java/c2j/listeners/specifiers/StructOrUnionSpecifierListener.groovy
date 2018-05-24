package c2j.listeners.specifiers

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait StructOrUnionSpecifierListener extends BaseListenerTrait {
    @Override
    void enterStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
        appendHiddenTokensToLeftOf ctx
        if (ctx.structDeclarationList() != null) {
            appendIfNotNull "public class ${ctx.Identifier() ?: "StructWrapper"} {"
        } else appendIfNotNull ctx.Identifier()
    }

    @Override
    void exitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
        if (ctx.structDeclarationList() != null) {
            appendIfNotNull "\n}"
        }
    }
}