package c2j.listeners

import c2j.antlrGenerated.CParser
import org.antlr.v4.runtime.tree.TerminalNode

trait FunctionDefinitionListener extends BaseListenerTrait {
    @Override
    void enterFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        appendIfNotNull "static "
    }

    @Override
    void exitFunctionDefinition(CParser.FunctionDefinitionContext ctx) {
        Optional<TerminalNode> mainFunction = Optional.ofNullable(
                ctx.getRuleContext(CParser.DeclaratorContext.class, 0)
                        ?.getRuleContext(CParser.DirectDeclaratorContext.class, 0)
                        ?.getRuleContext(CParser.DirectDeclaratorContext.class, 0)
                        ?.getTokens(105)
                        ?.stream()
                        ?.filter({ t -> (t?.getText() == "main") })
                        ?.findAny()
                        ?.orElse(null)
        )


        if (mainFunction.isPresent()) {
            def start = getBuffer().lastIndexOf("return")
            def end = getBuffer().lastIndexOf(";") + 1
            buffer.delete(start, end)
        }
    }
}