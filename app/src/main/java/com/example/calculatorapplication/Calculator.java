package com.example.calculatorapplication;


import java.util.Stack;

public class Calculator {

    public Double calculate(String expression) {
        String rpnExpression = rpn(expression);
        return calculateRpn(rpnExpression);

    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static  int getOperationPriority(String operation) {
        switch (operation) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    private String rpn(String expression) {
        String result = "";
        Stack<String> s = new Stack<String>();

        for (char ch: expression.toCharArray()) {
            String digit = String.valueOf(ch);
            if (isNumeric(digit)) {
                result += digit;
                continue;
            }
            result += " ";
            if (s.isEmpty()) {
                s.push(digit);
                continue;
            }

            String lastOperation = s.peek();
            int currentOperationPriority = getOperationPriority(digit);
            int lastOperationPriority = getOperationPriority(lastOperation);

            if (lastOperationPriority < currentOperationPriority) {
                s.push(digit);
                continue;
            }

            while (lastOperationPriority >= currentOperationPriority) {
                result += " ";
                result += s.pop();
                lastOperation = s.peek();
                lastOperationPriority = getOperationPriority(lastOperation);
            }
        }

        while (!s.isEmpty()) {
            result += " ";
            result += s.pop();
        }

        return result;
    }

    private double calculateRpn(String expression) {
        Stack<Double> s = new Stack<Double>();

        for (String digit: expression.split(" ")) {


            if (isNumeric(digit)) {
                Double number = Double.parseDouble(digit);
                s.push(number);
                continue;
            }

            Double firstOperand = s.pop();
            Double secondOperand = s.pop();
            s.push(calculateOperation(firstOperand, secondOperand, digit));
        }

        return s.pop();
    }

    private Double calculateOperation(Double first, Double second, String op) {
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return first/second;
        }
        return 0.0d;
    }

}
