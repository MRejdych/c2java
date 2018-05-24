package c2j

import c2j.c.CLexer
import c2j.c.CParser
import c2j.listeners.CLangListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTreeListener
import org.antlr.v4.runtime.tree.ParseTreeWalker

class Application {
    static void main(String[] args) throws IOException {
        String fileName = "hello.c"
        CLexer lexer = new CLexer(CharStreams.fromStream(Application.class.getResourceAsStream("/${fileName}")))
        TokenStream tokenStream = new CommonTokenStream(lexer)
        CParser parser = new CParser(tokenStream)
        parser.setBuildParseTree(true)

        CParser.CompilationUnitContext ctx = parser.compilationUnit()
        ParseTreeListener listener = new CLangListener(fileName, tokenStream)
        ParseTreeWalker.DEFAULT.walk(listener, ctx)
        print listener.getBuffer().toString()
    }
}
