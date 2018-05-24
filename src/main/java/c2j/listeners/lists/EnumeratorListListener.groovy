package c2j.listeners.lists

import c2j.J
import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait EnumeratorListListener extends BaseListenerTrait {
    @Override
    void enterEnumeratorList(CParser.EnumeratorListContext ctx) {

    }

    @Override
    void exitEnumeratorList(CParser.EnumeratorListContext ctx) {
        if (ctx.getParent() instanceof CParser.EnumeratorListContext) {
            def parent = ctx.getParent() as CParser.EnumeratorListContext
            appendIfNotNull parent.Comma(), J.COMMA
        }
    }
}