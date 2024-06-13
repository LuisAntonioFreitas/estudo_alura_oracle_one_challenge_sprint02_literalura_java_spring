package net.lanet.literalura.entity;

import jakarta.persistence.*;
import net.lanet.literalura.dto.LanguageDtoData;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_idiomas", indexes = {
        @Index(name = "idx_idioma", columnList = "idioma") })
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long id;
    @Column(name="idioma", nullable = false, unique = true)
    private String idioma;

    @ManyToMany(mappedBy = "idiomas",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();


    public Idioma() {}
    public Idioma(String idioma) {
        this.idioma = idioma;
    }
    public Idioma(LanguageDtoData model) {
        this.idioma = model.getLanguage();
    }
    public Idioma(Idioma model) { this.idioma = model.getIdioma(); }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) { this.idioma = idioma; }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

}
