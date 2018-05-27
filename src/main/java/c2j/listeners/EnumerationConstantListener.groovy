package c2j.listeners

import c2j.antlrGenerated.CParser

trait EnumerationConstantListener extends BaseListenerTrait {
    @Override
    void enterEnumerationConstant(CParser.EnumerationConstantContext ctx) {
        appendIfNotNull ctx.Identifier()
    }
}
