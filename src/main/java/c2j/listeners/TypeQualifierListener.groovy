package c2j.listeners

import c2j.J
import c2j.c.CParser

trait TypeQualifierListener extends BaseListenerTrait {

    @Override
    void enterTypeQualifier(CParser.TypeQualifierContext ctx) {

        appendIfNotNull ctx.Const(), J.FINAL
        appendIfNotNull ctx.Volatile(), J.VOLATILE
        appendIfNotNull ctx.Atomic(), J.SYNCHRONIZED, J.FINAL
    }

    @Override
    void exitTypeQualifier(CParser.TypeQualifierContext ctx) {
    }
}