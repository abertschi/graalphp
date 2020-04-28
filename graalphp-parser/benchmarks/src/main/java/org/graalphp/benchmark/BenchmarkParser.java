package org.graalphp.benchmark;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.develnext.jphp.core.syntax.SyntaxAnalyzer;
import org.develnext.jphp.core.tokenizer.Tokenizer;
import org.develnext.jphp.core.tokenizer.token.Token;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;
import php.runtime.common.LangMode;

import php.runtime.env.Context;
import php.runtime.env.Environment;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Parser Benchmark
 *
 * We test parsing speed of JPHP Parser and ANTRL of php source code
 *
 */
@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
//@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Fork(value = 1)
//@Timeout(time =30)
//@Warmup(iterations = 3)
//@Measurement(iterations = 5)
public class BenchmarkParser {

    public static void main(String[] args) throws RunnerException {

//        Options opt = new OptionsBuilder()
//                .include(BenchmarkParser.class.getSimpleName())
//                .forks(1)
//                .mode(G)
//                .build();
//
//        new Runner(opt).run();
    }

    @Param({
            "phpoffice/Xls.php",
            "benchmarkgame/binarytrees.php-1.php",
            "benchmarkgame/fannkuchredux.php-1.php",
            "test.php"
    }
    )
    public String filename;

    private String codeAntrl;
    private String codeJphp;

    @Setup()
    public void setup() {
        codeAntrl = readFile(filename);
        codeJphp = codeAntrl.replace("<?php", "");
        System.out.println(filename);
    }

    private String readFile(String path) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        try {
            return new Scanner(resource.openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            return null;
        }
    }

//    @Benchmark
    public void jphpParser(Blackhole sink) throws IOException {
//        String code = readFile("test2.php");
        Environment environment = new Environment();
        Tokenizer tokenizer = new Tokenizer(new Context(codeJphp));
        environment.scope.setLangMode(LangMode.DEFAULT);
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer(environment, tokenizer);
        sink.consume(analyzer);
    }

//    @Benchmark
    public void antrlParser(Blackhole sink) throws IOException {
        PhpLexer lexer = new PhpLexer(new ANTLRInputStream(codeAntrl));
        PhpParser parser = new PhpParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());
        PhpParser.PhpBlockContext unit = parser.phpBlock();
        sink.consume(unit);
    }

    @Benchmark
    public void antrlParserSwitchModes(Blackhole sink) throws IOException {
        PhpLexer lexer = new PhpLexer(new ANTLRInputStream(codeAntrl));
        PhpParser parser = new PhpParser(new CommonTokenStream(lexer));
        try {
            parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
            parser.setErrorHandler(new BailErrorStrategy());
            PhpParser.PhpBlockContext unit = parser.phpBlock();
            sink.consume(unit);
        } catch (RuntimeException e ) {
            parser.reset();
            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            parser.setErrorHandler(new DefaultErrorStrategy());
            PhpParser.PhpBlockContext unit = parser.phpBlock();
            sink.consume(unit);
        }
    }

    @Test
    public void eclipseTest() throws IOException {
        String code = readFile("benchmarkgame/binarytrees.php-1.php");
        long l1 = System.currentTimeMillis();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, );
        Program ast = parser.setSource(code.toCharArray());
    }

    @Test
    public void antrlParserTests() throws IOException {
//        String code = readFile("test.php");
//                String code = readFile("phpoffice/Xls.php");
                String code = readFile("benchmarkgame/binarytrees.php-1.php");
        long l1 = System.currentTimeMillis();
        PhpLexer lexer = new PhpLexer(new ANTLRInputStream(code));
        PhpParser parser = new PhpParser(new CommonTokenStream(lexer));
//         try to parse with SLL(*)
        //        listener.suppressErrors(true);
        try {
            parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
            parser.setErrorHandler(new BailErrorStrategy());
            PhpParser.PhpBlockContext unit = parser.phpBlock();
            long l2 = System.currentTimeMillis();
            System.out.println(l2 - l1);
            System.out.println(unit.toString());
            System.out.println("without exception");
            for(ParseTree u: unit.children) {
                System.out.println(u);
            }

        } catch (RuntimeException e ) {
            parser.reset();
            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
            parser.setErrorHandler(new DefaultErrorStrategy());
            PhpParser.PhpBlockContext unit = parser.phpBlock();
            long l2 = System.currentTimeMillis();
            System.out.println(l2 - l1);
            System.out.println(unit.toString());
            System.out.println("with exception");
            for(ParseTree u: unit.children) {
                System.out.println(u);
            }
        }
    }


    @Test
    public void jphpParserTests() throws IOException {
        long l1 = System.currentTimeMillis();
        String code = readFile("phpoffice/Xls.php");
//        String code = readFile("phpoffice/Xls.php");
//        String code = readFile("benchmarkgame/binarytrees.php-2.php");
//        String code = readFile("test.php");
        code = code.replace("<?php", "");
        Environment environment = new Environment();
        Tokenizer tokenizer = new Tokenizer(new Context(code));
        environment.scope.setLangMode(LangMode.DEFAULT);
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer(environment, tokenizer);
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        System.out.println(analyzer);
        for (Token t: analyzer.getTree()) {
            System.out.println(t.toString());
        }

    }
}
