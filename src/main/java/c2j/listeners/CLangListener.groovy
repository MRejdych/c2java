package c2j.listeners

import c2j.J
import c2j.c.CBaseListener
import c2j.listeners.declarations.ExternalDeclarationListener
import c2j.listeners.declarations.StaticAssertDeclarationListener
import c2j.listeners.expressions.*
import c2j.listeners.forComponents.ForExpressionListener
import c2j.listeners.lists.EnumeratorListListener
import c2j.listeners.lists.IdentifierListListener
import c2j.listeners.lists.ParameterListListener
import c2j.listeners.operators.AssignmentOperatorListener
import c2j.listeners.operators.UnaryOperatorListener
import c2j.listeners.specifiers.EnumSpecifierListener
import c2j.listeners.specifiers.StructOrUnionSpecifierListener
import c2j.listeners.specifiers.TypeSpecifierListener
import c2j.listeners.statements.*
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Vocabulary
import org.antlr.v4.runtime.tree.TerminalNode

class CLangListener
        extends CBaseListener
        implements
                AssignmentOperatorListener,
                CompilationUnitListener,
                EnumerationConstantListener,
                EnumerationListener,
                EnumSpecifierListener,
                JumpStatementListener,
                PrimaryExpressionListener,
                StructOrUnionSpecifierListener,
                TypedefNameListener,
                TypeQualifierListener,
                TypeSpecifierListener,
                UnaryOperatorListener,
                PostfixExpressionListener,
                UnaryExpressionListener,
                CastExpressionListener,
                MultiplicativeExpressionListener,
                AdditiveExpressionListener,
                ShiftExpressionListener,
                RelationalExpressionListener,
                EqualityExpressionListener,
                AndExpressionListener,
                ExclusiveOrExpressionListener,
                InclusiveOrExpressionListener,
                LogicalAndExpressionListener,
                LogicalOrExpressionListener,
                AssignmentExpressionListener,
                ExpressionListener,
                ConstantExpressionListener,
                LabeledStatementListener,
                ExpressionStatementListener,
                SelectionStatementListener,
                StatementListener,
                CompoundStatementListener,
                IterationStatementListener,
                IdentifierListListener,
                ForExpressionListener,
                ExternalDeclarationListener,
                StaticAssertDeclarationListener,
                DesignatorListener,
                EnumeratorListListener,
                ParameterListListener,
                NestedParenthesesBlockListener,
                DirectDeclaratorListener {
    String fileName
    CommonTokenStream tokenChannel
    Vocabulary java8Vocabulary
    StringBuilder buffer

    CLangListener(String fileName, CommonTokenStream tokenChannel) {
        this.fileName = fileName
        this.tokenChannel = tokenChannel
        this.java8Vocabulary = J.VOCABULARY
        this.buffer = new StringBuilder(10000)
    }

    @Override
    def appendHiddenTokensToLeftOf(ParserRuleContext ctx) {
        tokenChannel.getHiddenTokensToLeft(ctx.start.tokenIndex)?.forEach({ token -> appendIfNotNull token.getText() })
    }

    @Override
    def appendIfNotNull(TerminalNode node, int ... javaEquivalent) {
        if (node == null) return
        List<String> equivalents = []
        javaEquivalent.toSet()
                .forEach {
            i -> equivalents.add "${getFromJavaVocab(i as int).replaceAll("'", "")}"
        }
        String lastEl = equivalents.pop()
        equivalents.forEach({ el -> appendIfNotNull "${el} " })
        appendIfNotNull lastEl
    }

    @Override
    def appendIfNotNull(def value) {
        if (value != null) buffer.append(value)
    }

    @Override
    def getFromJavaVocab(int index) {
        return java8Vocabulary.getLiteralName(index)
    }
}
