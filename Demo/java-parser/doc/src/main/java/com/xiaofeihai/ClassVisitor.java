package com.xiaofeihai;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MarkerAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.javadoc.Javadoc;

import java.util.List;

/**
 * @author mingming.xu
 * @description:
 * @date 2021/1/5 16:49
 * @Version 1.0
 */

public class ClassVisitor extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(ClassOrInterfaceDeclaration clazz, Void arg) {
        System.out.println(clazz.getNameAsString());
        NodeList<AnnotationExpr> annotationExprs = clazz.getAnnotations();
        for(AnnotationExpr annotation: annotationExprs){
            String name = annotation.getNameAsString();
            if(annotation.isMarkerAnnotationExpr()){
                MarkerAnnotationExpr normalAnnotationExpr = annotation.asMarkerAnnotationExpr();
            }
            if(annotation.isNormalAnnotationExpr()){
                annotation.asNormalAnnotationExpr();
            }
            if(annotation.isSingleMemberAnnotationExpr()){
                SingleMemberAnnotationExpr singleMemberAnnotation = annotation.asSingleMemberAnnotationExpr();
                String value = singleMemberAnnotation.getMemberValue().toString();
            }
        }
        Comment comment = clazz.getComment().get();
        Javadoc doc = clazz.getJavadoc().get();
        doc.getBlockTags();
        doc.getDescription().toText();

        List<MethodDeclaration> methodDeclarations = clazz.getMethods();
        for(MethodDeclaration method: methodDeclarations){
            NodeList<Parameter> parameters = method.getParameters();
            method.getJavadoc().get().getBlockTags();
            // 方法访问级别
            method.getAccessSpecifier();
            // 响应
            method.getType().toString();

            method.getType();
        }
    }
}
