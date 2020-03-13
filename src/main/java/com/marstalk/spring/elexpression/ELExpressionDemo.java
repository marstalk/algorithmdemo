package com.marstalk.spring.elexpression;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ELExpressionDemo {

    public static void main(String[] args) {
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("'hello world'");
        Object value = expression.getValue();
        //hello world
        System.out.println(value);

        expression = spelExpressionParser.parseExpression("'hello world'.charAt(0)");
        // h
        System.out.println(expression.getValue());

        // 11
        System.out.println(spelExpressionParser.parseExpression("'hello world'.bytes.length").getValue());

        // abc
        System.out.println(spelExpressionParser.parseExpression("new String('abc')").getValue());
    }
}
