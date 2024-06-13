package net.lanet.literalura.main;

import net.lanet.literalura.service.*;
import net.lanet.literalura.utils.ValidNumber;

import java.util.Scanner;

public class Main {

    private final MenuService menuService;
    private final Scanner scanner = new Scanner(System.in);

    public Main(MenuService menuService) {
        this.menuService = menuService;
    }

    public final static String URL_BASE = "https://gutendex.com/books/";
    public final static String DISPLAY_LIVROS = """
            ----- LIVRO -----
            Título   : %s
            Autor(es): %s
            Idioma(s): %s
            Downloads: %d""";
    public final static String DISPLAY_AUTORES = """
            ----- AUTOR -----
            Nome       : %s
            Nascimento : %d
            Falecimento: %d
            Livro(s)   : %s""";

    public void viewMenu() {

        final String OPTIONS_MENU = """
            *****************************************************
            *                    LiterAlura                     *
            *****************************************************
            *  1  |  Buscar livro pelo título (API)             *
            *  2  |  Listar livros registrados                  *
            *  3  |  Listar autores registrados                 *
            *  4  |  Listar autores vivos em um determinado ano *
            *  5  |  Listar livros em um determinado idioma     *
            *  6  |  Listar Top 5 livros mais baixados          *
            *  7  |  Buscar livros registrados                  *
            *  8  |  Buscar autores registrados                 *
            *  9  |  Sair                                       *
            *****************************************************
            Escolha um número válido nas opções:""";
        final int OPTIONS_LIMIT = 9;

        int optionMenu = 0;
        while (optionMenu != OPTIONS_LIMIT) {
            System.out.println(OPTIONS_MENU);

            optionMenu = ValidNumber.getValidInteger(scanner, OPTIONS_LIMIT);

            switch (optionMenu) {
                case 1:
                    // Buscar livro pelo título (API)
                    menuService.buscarLivroPeloTitulo(scanner);
//                    buscarLivroPeloTitulo(scanner);
                    break;
                case 2:
                    // Listar livros registrados
                    menuService.listarLivrosRegistrados();
                    break;
                case 3:
                    // Listar autores registrados
                    menuService.listarAuroresRegistrados();
                    break;
                case 4:
                    // Listar autores vivos em um determinado ano
                    menuService.listarAutoresVivosEmUmDeterminadoAno(scanner);
                    break;
                case 5:
                    // Listar livros em um determinado idioma
                    menuService.listarLivrosEmUmDeterminadoIdioma(scanner);
                    break;
                case 6:
                    // Listar Top 5 livros mais baixados
                    menuService.listarTop5LivrosMaisBaixados();
                    break;
                case 7:
                    // Buscar livros registrados
                    menuService.buscarLivrosRegistrados(scanner);
                    break;
                case 8:
                    // Buscar autores registrados
                    menuService.buscarAutoresRegistrados(scanner);
                    break;
                case 9:
                    // Sair;
                    System.out.println("Aplicação finalizada!");
                    scanner.close();
                    break;
                default:
                    // Opção inválida
                    System.out.println("Opção inválida!");
                    break;
            }

        }

    }

}
