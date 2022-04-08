package com.andy;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Tokenizer tokenizer = new Tokenizer(
                List.of(
                        new IdentifierParser(),
                        new StringParser(),
                        new EOFParser(),
                        new StringMatchParser("class", TokenKind.Cls),
                        new StringMatchParser(",", TokenKind.Comma),
                        new StringMatchParser(";", TokenKind.Semicolon),
                        new StringMatchParser("{", TokenKind.LeftBrace),
                        new StringMatchParser("}", TokenKind.RightBrace),
                        new StringMatchParser("[", TokenKind.LeftBracket),
                        new StringMatchParser("]", TokenKind.RightBracket),
                        new StringMatchParser("(", TokenKind.LeftParenthesis),
                        new StringMatchParser(")", TokenKind.RightParenthesis),
                        new StringMatchParser("new", TokenKind.KeywordNew),
                        new StringMatchParser("=", TokenKind.Assignment),
                        new StringMatchParser("==", TokenKind.Equality),
                        new StringMatchParser(".", TokenKind.SubItemOperator),
                        new StringMatchParser("!=", TokenKind.NotEqualsOperator),
                        new StringMatchParser("::", TokenKind.MethodReferenceOperator),
                        new StringMatchParser("->", TokenKind.LambdaArrow),
                        new StringMatchParser("<=", TokenKind.LessThanOrEqualTo),
                        new StringMatchParser(">=", TokenKind.GreaterThanOrEqualTo),
                        new StringMatchParser(">", TokenKind.GreaterThan),
                        new StringMatchParser("<", TokenKind.LessThan),
                        new StringMatchParser("%", TokenKind.Modulus),
                        new StringMatchParser("||", TokenKind.LogicalOr),
                        new StringMatchParser("&&", TokenKind.LogicalAnd),
                        new StringMatchParser("!", TokenKind.LogicalNot),
                        new StringMatchParser("&", TokenKind.BinaryAnd),
                        new StringMatchParser("|", TokenKind.BinaryOr),
                        new StringMatchParser("^", TokenKind.BinaryXor),
                        new StringMatchParser("<<", TokenKind.BinaryShiftLeft),
                        new StringMatchParser(">>", TokenKind.BinaryShiftRight),
                        new StringMatchParser("~", TokenKind.BinaryComplement),
                        new StringMatchParser("*", TokenKind.MultiplicationOperand),
                        new StringMatchParser("-", TokenKind.SubtractionOperand),
                        new StringMatchParser("/", TokenKind.DivisionOperand),
                        new StringMatchParser("+", TokenKind.AdditionOperand),
                        new StringMatchParser(":", TokenKind.Colon),
                        new StringMatchParser("null", TokenKind.NullKeyword),
                        new StringMatchParser("if", TokenKind.If),
                        new StringMatchParser("else", TokenKind.Else),
                        new StringMatchParser("else if", TokenKind.ElseIf),
                        new StringMatchParser("do", TokenKind.Do),
                        new StringMatchParser("for", TokenKind.For),
                        new StringMatchParser("while", TokenKind.While),
                        new StringMatchParser("public", TokenKind.PublicAccessModifier),
                        new StringMatchParser("protected", TokenKind.ProtectedAccessModifier),
                        new StringMatchParser("private", TokenKind.PrivateAccessModifier),
                        new StringMatchParser("import", TokenKind.ImportKeyword),
                        new StringMatchParser("package", TokenKind.PackageKeyword),
                        new StringMatchParser("try", TokenKind.TryKeyword),
                        new StringMatchParser("catch", TokenKind.CatchKeyword),
                        new CharacterParser(),
                        new NumberParser(),
                        new AnnotationParser(),
                        new SingleLineCommentParser(),
                        new RangeBasedCommentParser(),
                        new GreedyWhitespaceParser()
                )
        );

        for (int i = 0; i < args.length; i++) {
            try {
                MutableTokenInputStream is = new ReadableTokenInputStream(new FileReader(args[i]));
                try {
                    System.out.println(args[i]);
                    System.out.println(tokenizer.parse(is));

                } catch (PartialTokenizerException e) {
                    e.printStackTrace();
                } catch (TokenizerException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


    }
}
