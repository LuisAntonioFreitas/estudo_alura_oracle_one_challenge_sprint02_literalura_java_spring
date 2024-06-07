package net.lanet.literalura.utils;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ValidString {
    public static String getValidString(Scanner scanner, Integer limit) {

        String text = "";
        boolean isValid = false;
        while (!isValid) {
            try {
                String input = scanner.nextLine();

                text = String.valueOf(input.trim());
                if (!text.isEmpty() && (limit == null || text.length() <= limit)) {
                    isValid = true;
                } else {
                    System.out.println("Digite um texto válido!");
                }
            } catch (Exception ignored) {
                System.out.println("Digite um texto válido!");
            }
        }

        return text;
    }
}
