package c2j.listeners

import c2j.antlrGenerated.CParser

trait CompilationUnitListener extends BaseListenerTrait {

    @Override
    void enterCompilationUnit(CParser.CompilationUnitContext ctx) {
        String className = fileName.replaceFirst("${fileName.charAt(0)}", "${fileName.charAt(0).toUpperCase()}")
                .replaceAll("\\..*", "")
        appendIfNotNull "public class ${className} {\n"
    }

    @Override
    void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
        appendIfNotNull "\n}\n"
    }
}