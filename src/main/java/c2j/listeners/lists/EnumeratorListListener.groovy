package c2j.listeners.lists

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait EnumeratorListListener extends BaseListenerTrait {
    @Override
    void exitEnumeratorList(CParser.EnumeratorListContext ctx) {
        if (ctx.getParent() instanceof CParser.EnumeratorListContext) {
            def parent = ctx.getParent() as CParser.EnumeratorListContext
            translateAndAppendIfNotNull([parent.Comma()], parent)
        }
    }
}