package c2j.listeners

import c2j.antlrGenerated.CParser

trait FunctionDefinitionListener extends BaseListenerTrait {
    @Override
    void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        appendIfNotNull "static "
    }
}