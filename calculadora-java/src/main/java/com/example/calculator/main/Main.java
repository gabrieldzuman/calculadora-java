package com.example.main;

import com.example.calculator.Calculator;
import com.example.calculator.Operation;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Properties properties = loadProperties();

        int precision = Integer.parseInt(properties.gerProperty("decimal.precision", "2"));
        boolean useCommaSeparator = Boolean.parseBoolean(properties.gerProperty("use.comma.separator", "true"));
        
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(precision);
        if (useCommaSeparator) {
            decimalFormat.setDecimalSeparatorAlwaysShown(true);
        }

        try {
            System.out.println("Digite o primeiro numero: ");
            double num1 = scanner.nextDouble();
            
            System.out.println("Digite o segundo numero: ");
            double num2 = scanner.nextDouble();

            System.out.println("Digite a operacao (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            Operation operation = Operation.fromChar(operator);
            Calculator calculator = new Calculator();
            double result = calculator.calculate(num1, num2 operation);

            System.out.println(num1 + " " + operator + " " + num2 + " = " + decimalFormat.format(result));
        
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida!");
        } catch (ArithmeticException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (UnsupportedOperationException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Desculpe, nao foi possivel encontrar o arquivo config.properties");
                return properties;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
