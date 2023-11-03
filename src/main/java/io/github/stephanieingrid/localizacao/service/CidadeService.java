package io.github.stephanieingrid.localizacao.service;

import io.github.stephanieingrid.localizacao.domain.entity.Cidade;
import io.github.stephanieingrid.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static io.github.stephanieingrid.localizacao.domain.repository.specs.CidadeSpecs.*;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;


    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    void salvarCidade() {

        var cidade = new Cidade(1L, "São Paulo", 11451245L);
        cidadeRepository.save(cidade);
    }

    public void listarCidadesPorQuantidadeHabitantes() {
        cidadeRepository
                .findByHabitantesLessThanAndNomeLike(2315561L, "Be%")
                .forEach(System.out::println);
    }

    public void listarCidadesPorNome() {
        cidadeRepository
                .findByNomeLike("s%", Sort.by("habitantes"))
                .forEach(System.out::println);

    }

    public void listarCidadesPorNomePage() {
        Pageable pegeable = PageRequest.of(0, 10);
        cidadeRepository
                .findByNomeLike("%%%%", Sort.by("habitantes"))
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes() {
        cidadeRepository.findByHabitantes(11451245L).forEach(System.out::println);

    }

    public void listarCidades() {
        cidadeRepository.findAll().forEach(System.out::println);
    }
    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepository.findAll(example);
    }

    public void listarCidadesByNomeSpec(){
        cidadeRepository
                .findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(1000L)))
                .forEach(System.out::println);
    }


    //select * from cidade where 1 = 1
    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs =
                Specification.where(((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));

        if (filtro.getId() != null){
            specs = specs.and(idEqual(( filtro.getId() )));
        }
        if (StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }
        if (filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        cidadeRepository.findAll(specs).forEach(System.out::println);
    }

}
