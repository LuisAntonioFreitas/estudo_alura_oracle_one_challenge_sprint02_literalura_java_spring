package net.lanet.literalura.main;

import net.lanet.literalura.service.*;
import net.lanet.literalura.utils.ValidNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

    private IMenuService menuService = new MenuService();

    private Scanner scanner = new Scanner(System.in);
    public final static String URL_BASE = "https://gutendex.com/books/";
    public final static String DISPLAY_LIVROS = """
            ----- LIVRO -----
            Título   : %s
            Autor(es): %s
            Idioma(s): %s
            Downloads: %d""";

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
            *  7  |  Sair                                       *
            *****************************************************
            Escolha um número válido nas opções:""";
        final int OPTIONS_LIMIT = 7;

        int optionMenu = 0;
        while (optionMenu != OPTIONS_LIMIT) {
            System.out.println(OPTIONS_MENU);

            optionMenu = ValidNumber.getValidInteger(scanner, OPTIONS_LIMIT);

            switch (optionMenu) {
                case 1:
                    // Buscar livro pelo título (API)
                    menuService.buscarLivroPeloTitulo(scanner);
                    break;
                case 2:
                    // Listar livros registrados
                    listarLivrosRegistrados();
                    break;
                case 3:
                    // Listar autores registrados
                    listarAuroresRegistrados();
                    break;
                case 4:
                    // Listar autores vivos em um determinado ano
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    // Listar livros em um determinado idioma
                    listarLivrosEmUmDeterminadoIdioma();
                    break;
                case 6:
                    // Listar Top 5 livros mais baixados
                    listarTop5LivrosMaisBaixados();
                    break;
                case 7:
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

    private void listarLivrosRegistrados() {
    }

    private void listarAuroresRegistrados() {
    }

    private void listarLivrosEmUmDeterminadoIdioma() {
    }

    private void listarAutoresVivosEmUmDeterminadoAno() {
    }

    private void listarTop5LivrosMaisBaixados() {
    }

}
