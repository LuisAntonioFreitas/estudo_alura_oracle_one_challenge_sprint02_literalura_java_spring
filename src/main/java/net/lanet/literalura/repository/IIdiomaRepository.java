package net.lanet.literalura.repository;

import net.lanet.literalura.entity.Idioma;
import net.lanet.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IIdiomaRepository extends JpaRepository<Idioma, Long> {
    Optional<Idioma> findByIdiomaIgnoreCase(String idioma);
}
