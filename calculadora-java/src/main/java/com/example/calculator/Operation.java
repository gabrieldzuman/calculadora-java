package com.example.calculator;

public enum Operation {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private final char symbol;

    Operation char getSymbol() {
        return symbol;
    }

    public static Operation fromChar(char symbol) {
        for (Operation operation : Operation.values()) {
            if (operation.getSymbol() == symbol){
                return operation;
            }
        }
        throw new IllegalArgumentException("Operacao nao reconhecida: " + symbol);
    }
}