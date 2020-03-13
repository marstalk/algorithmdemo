package com.marstalk.spring.elexpression;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class EvaluationContextDemo {
    public static void main(String[] args) {
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

        Role role = new Role(1L, "role_name", "note");
        Expression exp = spelExpressionParser.parseExpression("note");
        System.out.println(exp.getValue(role));
    }


}

class Role {
    private long id;
    private String name;
    private String note;

    public Role(long id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}