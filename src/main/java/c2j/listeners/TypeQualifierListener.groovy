package c2j.listeners

import c2j.c.CParser

trait TypeQualifierListener extends BaseListenerTrait {

    @Override
    void enterTypeQualifier(CParser.TypeQualifierContext ctx) {
        translateAndAppendIfNotNull([ctx.Const(), ctx.Volatile(), ctx.Atomic(), ctx.Restrict()], ctx)
    }

    @Override
    void exitTypeQualifier(CParser.TypeQualifierContext ctx) {
    }
}