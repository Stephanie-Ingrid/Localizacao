package io.github.stephanieingrid.localizacao.domain.repository;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import io.github.stephanieingrid.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {


    List<Cidade> findByNome(String nome);

    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLike(String nome);


    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);



    List<Cidade> findByHabitantes(Long habitantes);

}
