package c2j.listeners.specifiers

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait EnumSpecifierListener extends BaseListenerTrait {
    @Override
    void enterEnumSpecifier(CParser.EnumSpecifierContext ctx) {
        appendIfNotNull "public "
        appendIfNotNull ctx.Enum(), J.ENUM
        appendIfNotNull " ${ctx.Identifier() ?: "EnumWrapper"} {"
    }

    @Override
    void exitEnumSpecifier(CParser.EnumSpecifierContext ctx) {
        appendIfNotNull("}")
    }
}