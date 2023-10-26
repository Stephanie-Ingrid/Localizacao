package io.github.stephanieingrid.localizacao;

import io.github.stephanieingrid.localizacao.domain.entity.Cidade;
import io.github.stephanieingrid.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		listarCidadesPorQuantidadeHabitantes();
	}

	void listarCidadesPorQuantidadeHabitantes(){
		cidadeRepository.findByHabitantesGreaterThan(2315561L).forEach(System.out::println);
	}

	void listarCidadesPorNome() {
		cidadeRepository.findByNomeLike("belo%").forEach(System.out::println);


	}
	void listarCidadesPorHabitantes(){
		cidadeRepository.findByHabitantes(11451245L).forEach(System.out::println);

	}
	@Transactional
	void salvarCidade(){

		var cidade = new Cidade( 1L, "São Paulo",11451245L );
		cidadeRepository.save(cidade);
	}
	void listarCidades(){
		cidadeRepository.findAll().forEach(System.out::println);
	}
	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
