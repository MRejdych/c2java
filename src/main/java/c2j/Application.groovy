package c2j

import c2j.antlrGenerated.CLexer
import c2j.antlrGenerated.CParser
import c2j.listeners.CLangListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTreeListener
import org.antlr.v4.runtime.tree.ParseTreeWalker

import java.nio.file.Path
import java.nio.file.Paths

class Application {
    static void main(String[] args) throws IOException {
        List<String> argsList = Arrays.asList(args)
        Optional<String> inputFile = Optional.ofNullable(argsList[0])
        Optional<String> outputFilePackage = Optional.ofNullable(argsList[1])

        String fileName = inputFile.orElse "hello.c"
        String filePackage = outputFilePackage.orElse ""

        CLexer lexer = new CLexer(CharStreams.fromStream(Application.class.getResourceAsStream("/${fileName}")))
        TokenStream tokenStream = new CommonTokenStream(lexer)
        CParser parser = new CParser(tokenStream)
        parser.setBuildParseTree(true)

        CParser.CompilationUnitContext ctx = parser.compilationUnit()
        ParseTreeListener listener = new CLangListener(fileName, filePackage, tokenStream)
        ParseTreeWalker.DEFAULT.walk(listener, ctx)
        print listener.getResult()


        String outputFileName = fileName.replaceFirst("${fileName.charAt(0)}", "${fileName.charAt(0).toUpperCase()}")
                .replaceAll("\\..*", ".java")

        Path filePath = Paths.get("build/translated/java", filePackage.split("\\."))

        File dir = filePath.toFile()
        dir.mkdirs()
        File out = filePath.resolve(outputFileName).toFile()
        out.createNewFile()
        out.write(listener.getResult())
    }
}
