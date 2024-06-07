package net.lanet.literalura.service;

import net.lanet.literalura.dto.ResponseDtoBook;
import net.lanet.literalura.dto.BookDtoData;
import net.lanet.literalura.entity.Autor;
import net.lanet.literalura.entity.Idioma;
import net.lanet.literalura.entity.Livro;
import net.lanet.literalura.enums.SimNao;
import net.lanet.literalura.utils.ValidEnum;
import net.lanet.literalura.utils.ValidString;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static net.lanet.literalura.main.Main.URL_BASE;

@Service
public class MenuService implements IMenuService {

    private IConvertsData convertsData = new ConvertsData();

    @Override
    public void buscarLivroPeloTitulo(Scanner scanner) {
        boolean searchPlus = true;
        while (searchPlus) {
            System.out.println("Informe o título do livro:");
            String title = ValidString.getValidString(scanner, 100);
            String search = URLEncoder.encode(title, StandardCharsets.UTF_8).trim();

            String json = ConsumeApi.getData(URL_BASE.concat("?search=%s".formatted(search)));
            if (!json.isEmpty() && !json.trim().equalsIgnoreCase("")) {
                ResponseDtoBook response = convertsData.getDataJsonToClass(json, ResponseDtoBook.class);
                List<BookDtoData> listBooks = response.getResults();
                if (!listBooks.isEmpty()) {
                    listBooks.forEach(System.out::println);

//                    listBooks.forEach(e -> System.out.println(e.toString()));

//                    listBooks.stream()
//                            .forEach(e -> System.out.println(
//                                    DISPLAY_LIVROS.formatted(
//                                    e.getTitle(),
//                                    e.getAuthors().stream().map(j -> j.getName()).collect(Collectors.joining(" | ")),
//                                    e.getLanguages().stream().map(k -> k.getLanguage()).collect(Collectors.joining(" | ")),
//                                    e.getDownloadCount()))
//                            );

                    System.out.println("Registrar livros encontrados (Sim/Não):");
                    SimNao simNaoSalvar = ValidEnum.getValidEnumSimNao(scanner);
                    if (String.valueOf(simNaoSalvar).equalsIgnoreCase("sim")) {

                        List<Livro> listaLivros = new ArrayList<>();
                        listBooks.forEach(e -> {
                            Livro livro = new Livro(e);
                            e.getAuthors().forEach(j -> {
                                Autor autor = new Autor(j);
                                livro.addAutor(autor);
                            });
                            e.getLanguages().forEach(k -> {
                                Idioma idioma = new Idioma(k);
                                livro.addIdioma(idioma);
                            });
                            listaLivros.add(livro);
                        });
                        listaLivros.forEach(System.out::println);

//            Artista artista = new Artista(nome, tipo);
//            repository.save(artista);
                    }

                } else {
                    System.out.println("Nenhum livro encontrado!");
                }
            } else {
                System.out.println("Nenhum livro encontrado!");
            }

            System.out.println("Buscar novo livro (Sim/Não):");
            SimNao simNaoBuscar = ValidEnum.getValidEnumSimNao(scanner);
            if (String.valueOf(simNaoBuscar).equalsIgnoreCase("nao")) {
                searchPlus = false;
            }
        }
    }
}
