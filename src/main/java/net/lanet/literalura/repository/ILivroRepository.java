package net.lanet.literalura.repository;

import net.lanet.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);
    List<Livro> findTop2ByOrderByQuantidadeDownloadDesc();
    List<Livro> findByIdiomas(String idioma);
}
