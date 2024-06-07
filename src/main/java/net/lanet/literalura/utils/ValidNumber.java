package net.lanet.literalura.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ValidNumber {
    public static int getValidInteger(Scanner scanner, Integer limit) {

        int number = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                String input = scanner.nextLine();

                if (input.trim().contains(String.valueOf(","))
                        || input.trim().contains(String.valueOf("."))) {
                    input = input.trim().replace(".", "").replace(",","");
                }

                number = Integer.valueOf(input.trim());
                if (number > 0 && (limit == null || number <= limit)) {
                    isValid = true;
                } else {
                    System.out.println("Digite um número válido!");
                }
            } catch (Exception ignored) {
                System.out.println("Digite um número válido!");
            }
        }

        return number;
    }

    public static double getValidDouble(Scanner scanner, Integer limit) {

        double number = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                String input = scanner.nextLine();

                if (input.trim().contains(String.valueOf(","))) {
                    input = input.trim().replace(".", "").replace(",",".");
                }

                number = Double.valueOf(input.trim());
                if (number > 0 && (limit == null || number <= limit)) {
                    isValid = true;
                } else {
                    System.out.println("Digite um valor válido!");
                }
            } catch (Exception ignored) {
                System.out.println("Digite um valor válido!");
            }
        }

        return number;
    }
}
