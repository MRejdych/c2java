package c2j

import c2j.java8.java8Lexer
import org.antlr.v4.runtime.CharStream

//Wrapper class to shorten class name used to get static elements from java8Lexer
class J extends java8Lexer {
    J(CharStream input) {
        super(input)
    }
}
