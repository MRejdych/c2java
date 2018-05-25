package c2j.listeners

import c2j.c.CParser

trait EnumerationConstantListener extends BaseListenerTrait {
    @Override
    void enterEnumerationConstant(CParser.EnumerationConstantContext ctx) {
        appendIfNotNull ctx.Identifier()
    }
}
