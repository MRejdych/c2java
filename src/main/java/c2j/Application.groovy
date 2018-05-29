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

        String filePackage = outputFilePackage.orElse ""

        if (inputFile.isPresent()) {
            translateFile(inputFile.get(), filePackage)
        } else {
            String baseDir = "/c"
            String resourcesDir = Application.class.getResource(baseDir).getPath()
            new File(resourcesDir).list().toList().parallelStream()
                    .map { file -> "${baseDir}/${file}" }
                    .forEach { file -> translateFile(file as String, filePackage) }
        }
    }

    static def translateFile(String fileName, String filePackage) {
        CLexer lexer = new CLexer(CharStreams.fromStream(Application.class.getResourceAsStream("${fileName}")))
        TokenStream tokenStream = new CommonTokenStream(lexer)
        CParser parser = new CParser(tokenStream)
        parser.setBuildParseTree(true)
        def file = fileName.replaceFirst(".*/", "")
        file = file.replaceFirst("${file.charAt(0)}", "${file.charAt(0).toUpperCase()}")
                .replaceAll("\\..*", "")

        CParser.CompilationUnitContext ctx = parser.compilationUnit()
        ParseTreeListener listener = new CLangListener(file, filePackage, tokenStream)
        ParseTreeWalker.DEFAULT.walk(listener, ctx)
        String result = listener.getResult()
        String outputFileName = file + ".java"

        Path filePath = Paths.get("build/translated/java", filePackage.split("\\."))

        File dir = filePath.toFile()
        dir.mkdirs()
        File out = filePath.resolve(outputFileName).toFile()
        if (out.exists()) out.delete()
        out.createNewFile()
        out.write(result)
    }
}
