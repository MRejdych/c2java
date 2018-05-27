package c2j.listeners.specifiers

import c2j.antlrGenerated.CParser
import c2j.listeners.BaseListenerTrait

trait StructOrUnionSpecifierListener extends BaseListenerTrait {
    @Override
    void enterStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {

        if (ctx.structDeclarationList() != null) {
            appendIfNotNull "public static class ${ctx.Identifier() ?: "StructWrapper"} {"
        } else {
            appendIfNotNull ctx.Identifier()
            setClassName(ctx.Identifier().getText())
        }
    }

    @Override
    void exitStructOrUnionSpecifier(CParser.StructOrUnionSpecifierContext ctx) {
        if (ctx.structDeclarationList() != null) {
            appendIfNotNull "\n}"
        }
    }
}