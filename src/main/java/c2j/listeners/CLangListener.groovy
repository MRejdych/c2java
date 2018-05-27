package c2j.listeners

import c2j.CToJavaVocabulary
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
    StringBuilder buffer
    List<Integer> handledTokens
    Set<Integer> ignoredTokens
    private String className


    CLangListener(String fileName, CommonTokenStream tokenChannel) {
        this.fileName = fileName
        this.tokenChannel = tokenChannel
        this.buffer = new StringBuilder(10000)
        this.handledTokens = new ArrayList<>()
        this.ignoredTokens = new HashSet<>()
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
    def appendIfNotNull(def value) {
        if (value != null) buffer.append(value)
    }

    @Override
    void translateAndAppendIfNotNull(List<TerminalNode> terminalNodes, ParserRuleContext ctx) {
        terminalNodes.stream().map({ node -> node?.symbol?.type })
                .filter({ el -> el != null })
                .map({ i -> CToJavaVocabulary.translateFromCToJava(i as int) })
                .map({ string -> replaceUnhandledStatementPlaceholderIfPresent(string as String, Optional.ofNullable(ctx)) })
                .forEachOrdered({ string -> appendIfNotNull(string) })
    }

    private String replaceUnhandledStatementPlaceholderIfPresent(String input, Optional<ParserRuleContext> ctx) {
        ctx.map({ context ->
            if (input.contains(CToJavaVocabulary.unsupportedPlaceholder)) {
                String modified = input.replace(CToJavaVocabulary.unsupportedPlaceholder, concatenateParserContextChildrenTokens(context))
                ignoredTokens.addAll(context.start.tokenIndex..context.stop.tokenIndex)
                return modified
            } else return input
        }
        ).orElse(input)
    }

    private String concatenateParserContextChildrenTokens(ParserRuleContext ctx) {
        StringBuilder output = new StringBuilder()
        ctx?.children?.forEach({ child ->
            output.append child?.getText()
            output.append " "
        })
        return output.toString()
    }

    @Override
    def setClassName(String name) {
        return className = name
    }

    @Override
    String getClassNameIfPreceding() {
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
