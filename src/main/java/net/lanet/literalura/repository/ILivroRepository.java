package net.lanet.literalura.repository;

import net.lanet.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloIgnoreCase(String titulo);
    List<Livro> findTop5ByOrderByQuantidadeDownloadDesc();
    @Query(value = """
           SELECT l
             FROM Livro l
             JOIN l.idiomas i
            WHERE ( UPPER(TRIM(i.idioma)) = UPPER(TRIM(:idioma)) )
            """, nativeQuery = false)
    List<Livro> queryFindByIdioma(@Param("idioma") String idioma);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
