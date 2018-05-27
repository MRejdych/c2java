package c2j.listeners.lists

import c2j.c.CParser
import c2j.listeners.BaseListenerTrait

trait ParameterListListener extends BaseListenerTrait {
    @Override
    void exitParameterList(CParser.ParameterListContext ctx) {
        if (ctx.getParent() instanceof CParser.ParameterListContext) {
            def parent = ctx.getParent() as CParser.ParameterListContext
            translateAndAppendIfNotNull([parent.Comma()])
        }
    }
}
