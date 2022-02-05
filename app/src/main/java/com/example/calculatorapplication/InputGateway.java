package com.example.calculatorapplication;

public class InputGateway {

    private String buffer;

    public InputGateway() {
        buffer = "";
    }

    public void addSymbol(String symbol) {
        buffer += symbol;
    }

    public void clear() {
        buffer = "";
    }

    public String getData() {
        return buffer;
    }
}
