package c2j.listeners

import c2j.J
import c2j.c.CBaseListener
import c2j.listeners.declarations.ExternalDeclarationListener
import c2j.listeners.declarations.StaticAssertDeclarationListener
import c2j.listeners.expressions.*
import c2j.listeners.lists.ArgumentExpressionListListener
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

import java.util.regex.Pattern

class CLangListener
        extends CBaseListener
        implements
                AssignmentOperatorListener,
                CompilationUnitListener,
                EnumerationConstantListener,
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
                DirectDeclaratorListener,
                InitializerListener,
                DeclarationListener,
                GenericSelectionListener,
                ArgumentExpressionListListener,
                FunctionDefinitionListener {
    String fileName
    CommonTokenStream tokenChannel
    Vocabulary java8Vocabulary
    StringBuilder buffer
    List<Integer> handledTokens
    private String className


    CLangListener(String fileName, CommonTokenStream tokenChannel) {
        this.fileName = fileName
        this.tokenChannel = tokenChannel
        this.java8Vocabulary = J.VOCABULARY
        this.buffer = new StringBuilder(10000)
        this.handledTokens = new ArrayList<>()
    }

    @Override
    void enterEveryRule(ParserRuleContext ctx) {
        int startTokenIndex = ctx.start.tokenIndex
        if (!handledTokens.contains(startTokenIndex)) {
            handledTokens.add(startTokenIndex)
            appendHiddenTokensToLeftOf(ctx)
        }
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

    @Override
    def setClassName(String name) {
        return className = name
    }

    @Override
    String getClassNameIfPreceeding() {
        String name = className ? new String(className) : null
        className = null
        return name
    }

    String getResult() {
        String result = buffer.toString()
        Pattern funDeclarations = Pattern.compile("(int|short|long|double|float|void|boolean|char)\\s+([a-zA-Z_]*)(\\()([a-zA-Z0-9\\s,]*)(\\));")
        result = result.replaceAll(funDeclarations, "")
        result = result.replace("static int main()", "public static void main(String[] args)")
        return result
    }
}
