package c2j.listeners

import c2j.c.CListener
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode

trait BaseListenerTrait implements CListener {
    abstract def appendHiddenTokensToLeftOf(ParserRuleContext ctx)

    abstract def appendIfNotNull(TerminalNode node, int ... javaEquivalent)

    abstract def appendIfNotNull(def value)

    abstract def getFromJavaVocab(int index)

    abstract String getClassNameIfPreceeding()

    abstract def setClassName(String name)

    abstract String getFileName()
}