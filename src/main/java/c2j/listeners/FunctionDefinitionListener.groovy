package c2j.listeners

import c2j.c.CParser

trait FunctionDefinitionListener extends BaseListenerTrait {
    @Override
    void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        appendIfNotNull "static "
    }
}