package com.example.calculator;

public class Calculator {

    public double calculate(double num1, double num2, Operation operation) {
        switch (operation) {
            case ADD:
                return num1 + num2;
            case SUBTRACT:
                return num1 - num2;
            case MULTIPLY:
                return num1 * num2;
            case DIVIDE:
                if (num2 == 0) {
                    throw new ArithmeticException("Divisao por zero nao é permitida!");
                }    
                return num1 / num2;
            default:
            throw new UnsupportedOperationException("Operacao inválida!"); 
        }
    }
}