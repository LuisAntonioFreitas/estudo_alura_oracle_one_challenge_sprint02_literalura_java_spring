package net.lanet.literalura.repository;

import net.lanet.literalura.dto.AuthorDtoData;
import net.lanet.literalura.entity.Autor;
import net.lanet.literalura.entity.Idioma;
import net.lanet.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeIgnoreCase(String nome);
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer ano1, Integer ano2);
    List<Autor> findByNomeContainingIgnoreCase(String nome);
}
