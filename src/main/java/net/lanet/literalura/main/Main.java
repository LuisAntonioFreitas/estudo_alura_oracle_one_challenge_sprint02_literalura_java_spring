package net.lanet.literalura.main;

import net.lanet.literalura.utils.ValidNumber;

import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);

    public void viewMenu() {

        final String OPTIONS_MENU = """
                ******************************************
                *               LiterAlura               *
                ******************************************
                *  1  |  Buscar livro pelo título (API)  *
                *  2  |  Listar livros registrados       *
                *  3  |  Listar autores registrados      *
                *  4  |  Listar autores vivos em um      *
                *        determinado ano                 *
                *  5  |  Listar livros em um determinado *
                *        idioma                          *
                *  6  |  Sair                            *
                ******************************************
                Escolha um número válido nas opções:""";

        final int OPTIONS_LIMIT = 6;

        int optionMenu = 0;
        while (optionMenu != OPTIONS_LIMIT) {
            System.out.println(OPTIONS_MENU);

            optionMenu = ValidNumber.getValidInteger(scanner, OPTIONS_LIMIT);

            switch (optionMenu) {
                case 1:
                    // Buscar livro pelo título (API)
                    buscarLivroPeloTitulo();
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
                    // Sair;
                    System.out.println("Aplicação finalizada!");
                    break;
                default:
                    // Opção inválida
                    System.out.println("Opção inválida!");
                    break;
            }

        }

    }

    private void buscarLivroPeloTitulo() {
    }

    private void listarLivrosRegistrados() {
    }

    private void listarAuroresRegistrados() {
    }

    private void listarLivrosEmUmDeterminadoIdioma() {
    }

    private void listarAutoresVivosEmUmDeterminadoAno() {
    }

}
