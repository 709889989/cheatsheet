package com.xiaofeihai;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws FileNotFoundException {
        test();
    }

    public static void test() throws FileNotFoundException {
        FileInputStream file = new FileInputStream("D:\\IDEA\\doc\\src\\main\\resources\\Test.java");
        JavaParser parser = new JavaParser();
        ParseResult<CompilationUnit> result = parser.parse(file);
        CompilationUnit compilationUnit = result.getResult().get();

        VoidVisitor visitor = new ClassVisitor();
        visitor.visit(compilationUnit, null);
    }
}
