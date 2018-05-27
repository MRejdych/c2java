package c2j.listeners

import c2j.c.CListener
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode

trait BaseListenerTrait implements CListener {
    abstract def appendHiddenTokensToLeftOf(ParserRuleContext ctx)

    abstract def appendIfNotNull(def value)

    abstract String getClassNameIfPreceeding()

    abstract def setClassName(String name)

    abstract String getFileName()

    abstract void translateAndAppendIfNotNull(List<TerminalNode> terminalNodes)
}