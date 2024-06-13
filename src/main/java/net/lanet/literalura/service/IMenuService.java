package net.lanet.literalura.service;

import net.lanet.literalura.repository.IAutorRepository;
import net.lanet.literalura.repository.IIdiomaRepository;
import net.lanet.literalura.repository.ILivroRepository;

import java.util.Scanner;

public interface IMenuService {
    void buscarLivroPeloTitulo(Scanner scanner);
    void listarLivrosRegistrados();
    void listarAuroresRegistrados();
    void listarAutoresVivosEmUmDeterminadoAno(Scanner scanner);
    void listarLivrosEmUmDeterminadoIdioma(Scanner scanner);
    void listarTop5LivrosMaisBaixados();
}
