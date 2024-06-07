package net.lanet.literalura.entity;

import jakarta.persistence.*;
import net.lanet.literalura.dto.BookDtoData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.lanet.literalura.main.Main.DISPLAY_LIVROS;

@Entity
@Table(name="tb_livros", indexes = {
        @Index(name = "idx_titulo", columnList = "titulo") })
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long id;
    @Column(name="titulo", nullable = false, unique = true)
    private String titulo;
    @Column(name="quantidade_download")
    private Integer quantidadeDownload;

    @ManyToMany
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tb_livro_idioma",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "idioma_id"))
    private List<Idioma> idiomas = new ArrayList<>();


    public Livro() {}
    public Livro(String titulo,
                 Integer quantidadeDownload,
                 List<Autor> autores,
                 List<Idioma> idiomas) {
        this.titulo = titulo;
        this.quantidadeDownload = quantidadeDownload;
        this.autores = autores;
        this.idiomas = idiomas;
    }
    public Livro(BookDtoData model) {
        this.titulo = model.getTitle();
        this.quantidadeDownload = model.getDownloadCount();
    }

    @Override
    public String toString() {
        return DISPLAY_LIVROS.formatted(
                this.titulo,
                this.autores.stream().map(e -> e.getNome()).collect(Collectors.joining(" | ")),
                this.idiomas.stream().map(e -> e.getIdioma()).collect(Collectors.joining(" | ")),
                this.quantidadeDownload
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getQuantidadeDownload() {
        return quantidadeDownload;
    }

    public void setQuantidadeDownload(Integer quantidadeDownload) {
        this.quantidadeDownload = quantidadeDownload;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    public void addAutor(Autor autor) {
        if (!autor.getLivros().contains(this)) {
            this.autores.add(autor);
        }
    }
    public void removeAutor(Autor autor) {
        if (!autor.getLivros().contains(this)) {
            this.autores.remove(autor);
        }
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }
    public void addIdioma(Idioma idioma) {
        if (!idioma.getLivros().contains(this)) {
            this.idiomas.add(idioma);
        }
    }
    public void removeIdioma(Idioma idioma) {
        if (!idioma.getLivros().contains(this)) {
            this.idiomas.remove(idioma);
        }
    }

}
