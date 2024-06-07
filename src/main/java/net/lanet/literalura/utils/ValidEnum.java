package net.lanet.literalura.utils;

import net.lanet.literalura.enums.SimNao;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ValidEnum {

    public static <T extends Enum<T>> T getValidEnum(Scanner scanner, Class<T> classEnum) {

        T text = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                String input = scanner.nextLine();

                text = Enum.valueOf(classEnum, input.trim().toUpperCase());
                isValid = true;
            } catch (Exception ignored) {
                System.out.println("Digite um texto válido!");
            }
        }

        return text;
    }

    public static SimNao getValidEnumSimNao(Scanner scanner) {

        SimNao text = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                String input = scanner.nextLine();

                text = SimNao.fromString(input.trim().toUpperCase());
                if (text != null) {
                    isValid = true;
                } else {
                    System.out.println("Digite [S]im ou [N]ão!");
                }
            } catch (Exception ignored) {
                System.out.println("Digite [S]im ou [N]ão!");
            }
        }

        return text;
    }

}
