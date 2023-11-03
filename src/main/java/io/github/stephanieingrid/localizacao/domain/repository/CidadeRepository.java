package io.github.stephanieingrid.localizacao.domain.repository;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import io.github.stephanieingrid.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {


    @Query(nativeQuery = true, value ="select * from tb_cidade as c where c.nome =:nome" )
    List<Cidade> findByNomeSqlNativo (@Param("nome") String nome);


    List<Cidade> findAll(Specification<Cidade> nome);

    List<Cidade> findByNome(String nome);

    //Buscar pelo nome like ordenado
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    //Busca pelo nome like paginad
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);


    List<Cidade> findByNomeStartingWith(String nome);

    List<Cidade> findByNomeEndingWith(String nome);

    List<Cidade> findByNomeContaining(String nome);


    List<Cidade> findByHabitantes(Long habitantes);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);


}
