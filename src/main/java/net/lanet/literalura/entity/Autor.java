package net.lanet.literalura.entity;

import jakarta.persistence.*;
import net.lanet.literalura.dto.AuthorDtoData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.lanet.literalura.main.Main.DISPLAY_AUTORES;

@Entity
@Table(name="tb_autores", indexes = {
        @Index(name = "idx_nome", columnList = "nome") })
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long id;
    @Column(name="nome", nullable = false, unique = true)
    private String nome;
    @Column(name="ano_nascimento")
    private Integer anoNascimento;
    @Column(name="ano_falecimento")
    private Integer anoFalecimento;

    @ManyToMany(mappedBy = "autores",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();


    public Autor() {}
    public Autor(String nome,
                 Integer anoNascimento,
                 Integer anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
     }
    public Autor(AuthorDtoData model) {
        this.nome = model.getName();
        this.anoNascimento = model.getBirthYear();
        this.anoFalecimento = model.getDeathYear();
    }
    public Autor(Autor model) {
        this.nome = model.getNome();
        this.anoNascimento = model.getAnoNascimento();
        this.anoFalecimento = model.getAnoFalecimento();
    }

    @Override
    public String toString() {
        return DISPLAY_AUTORES.formatted(
                this.nome,
                this.anoNascimento,
                this.anoFalecimento,
                this.livros.stream().map(e -> e.getTitulo()).collect(Collectors.joining(" | "))
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
