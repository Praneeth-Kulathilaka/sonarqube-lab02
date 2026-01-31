package com.example;

public class Calculator {

    // EVEN WORSE: longer, more complex, duplicated logic
    public int calculate(int a, int b, String op) {
        switch (op) {
            case "add" -> {
                return a + b;
            }
            case "add-again" -> {
                return a + b; // DUPLICATION
            }
            case "sub" -> {
                return a - b;
            }
            case "sub-again" -> {
                return a - b; // DUPLICATION
            }
            case "mul" -> {
                return a * b;
            }
            case "div" -> {
                if(b == 0) {
                    return 0;
                } else {
                    return a / b;
                }
            }
            case "mod" -> {
                return a % b;
            }
            case "pow" -> {
                int result = 1;
                for(int i = 0; i < b; i++) {
                    result = result * a;
                }
                return result;
            }
            default -> {
                return 0;
            }
        }
    }

    // INTENTIONAL DUPLICATION
    public int addAgain(int a, int b) {
        return a + b;
    }
}
