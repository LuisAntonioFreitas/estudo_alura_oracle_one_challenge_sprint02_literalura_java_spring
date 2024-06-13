package net.lanet.literalura.service;

import net.lanet.literalura.dto.ResponseDtoBook;
import net.lanet.literalura.dto.BookDtoData;
import net.lanet.literalura.entity.Autor;
import net.lanet.literalura.entity.Idioma;
import net.lanet.literalura.entity.Livro;
import net.lanet.literalura.enums.SimNao;
import net.lanet.literalura.repository.IAutorRepository;
import net.lanet.literalura.repository.IIdiomaRepository;
import net.lanet.literalura.repository.ILivroRepository;
import net.lanet.literalura.utils.ValidEnum;
import net.lanet.literalura.utils.ValidNumber;
import net.lanet.literalura.utils.ValidString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static net.lanet.literalura.main.Main.URL_BASE;

@Service
@Transactional
public class MenuService implements IMenuService {
    private final IConvertsData convertsData = new ConvertsData();

    @Autowired
    private ILivroRepository repositoryLivro;
    @Autowired
    private IAutorRepository repositoryAutor;
    @Autowired
    private IIdiomaRepository repositoryIdioma;

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
                    SimNao simNaoRegistrar = ValidEnum.getValidEnumSimNao(scanner);
                    if (String.valueOf(simNaoRegistrar).equalsIgnoreCase("sim")) {
                        registrarLivros(listBooks);
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

    public void registrarLivros(List<BookDtoData> listBooks) {

        try {
            boolean saveBooks = true;
            listBooks.forEach(l -> {
                Optional<Livro> searchLivro = repositoryLivro.findByTituloIgnoreCase(l.getTitle());
                if (searchLivro.isEmpty()) {
                    Livro livro = new Livro();
                    livro.setTitulo(l.getTitle());
                    livro.setQuantidadeDownload(l.getDownloadCount());

                    l.getAuthors().forEach(a -> {
                        Optional<Autor> searchAutor = repositoryAutor.findByNomeIgnoreCase(a.getName());
                        Autor autor = searchAutor.orElseGet(() -> new Autor(a.getName(), a.getBirthYear(), a.getDeathYear()));
                        livro.addAutor(autor);
                    });

                    l.getLanguages().forEach(i -> {
                        Optional<Idioma> searchIdioma = repositoryIdioma.findByIdiomaIgnoreCase(i.getLanguage());
                        Idioma idioma = searchIdioma.orElseGet(() -> new Idioma(i.getLanguage()));
                        livro.addIdioma(idioma);
                    });

                    repositoryLivro.save(livro);
                    System.out.println("Livro '%s' registrado com sucesso!".formatted(l.getTitle()));
                } else {
                    System.out.println("Livro '%s' já registrados anteriormente!".formatted(l.getTitle()));
                }
            });
        } catch (Exception ignored) {
            System.out.println("Erro ao registrar livros!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void listarLivrosRegistrados() {
        List<Livro> livros = repositoryLivro.findAll();
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro registrado!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void listarAuroresRegistrados() {
        List<Autor> autores = repositoryAutor.findAll();
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor registrado!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void listarAutoresVivosEmUmDeterminadoAno(Scanner scanner) {
        System.out.println("Informe o ano:");
        Integer anoAtual = Integer.valueOf(Year.now().toString());
        Integer ano = ValidNumber.getValidInteger(scanner, anoAtual);
        List<Autor> autores = repositoryAutor
                .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor encontrado!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void listarLivrosEmUmDeterminadoIdioma(Scanner scanner) {
        System.out.println("Informe o idioma (Siglas - Ex.: pt|en|es|fr):");
        String idioma = ValidString.getValidString(scanner, 2);
        List<Livro> livros = repositoryLivro.queryFindByIdioma(idioma);
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void listarTop5LivrosMaisBaixados() {
        List<Livro> livros = repositoryLivro.findTop5ByOrderByQuantidadeDownloadDesc();
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro registrado!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void buscarLivrosRegistrados(Scanner scanner) {
        boolean searchPlus = true;
        while (searchPlus) {
            System.out.println("Informe o título do livro:");
            String titulo = ValidString.getValidString(scanner, 100);
            List<Livro> livros = repositoryLivro.findByTituloContainingIgnoreCase(titulo);
            if (!livros.isEmpty()) {
                livros.forEach(System.out::println);
            } else {
                System.out.println("Nenhum livro encontrado!");
            }

            System.out.println("Buscar outro livro (Sim/Não):");
            SimNao simNaoBuscar = ValidEnum.getValidEnumSimNao(scanner);
            if (String.valueOf(simNaoBuscar).equalsIgnoreCase("nao")) {
                searchPlus = false;
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void buscarAutoresRegistrados(Scanner scanner) {
        boolean searchPlus = true;
        while (searchPlus) {
            System.out.println("Informe o nome do autor:");
            String nome = ValidString.getValidString(scanner, 100);
            List<Autor> autores = repositoryAutor.findByNomeContainingIgnoreCase(nome);
            if (!autores.isEmpty()) {
                autores.forEach(System.out::println);
            } else {
                System.out.println("Nenhum autor encontrado!");
            }

            System.out.println("Buscar outro autor (Sim/Não):");
            SimNao simNaoBuscar = ValidEnum.getValidEnumSimNao(scanner);
            if (String.valueOf(simNaoBuscar).equalsIgnoreCase("nao")) {
                searchPlus = false;
            }
        }
    }

}
