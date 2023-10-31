package io.github.stephanieingrid.localizacao.service;

import io.github.stephanieingrid.localizacao.domain.entity.Cidade;
import io.github.stephanieingrid.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .findByNomeLike("s%", Sort.by("habitantes"))
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes() {
        cidadeRepository.findByHabitantes(11451245L).forEach(System.out::println);

    }

    void listarCidades() {
        cidadeRepository.findAll().forEach(System.out::println);
    }
}
