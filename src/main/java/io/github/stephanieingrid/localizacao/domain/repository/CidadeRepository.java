package io.github.stephanieingrid.localizacao.domain.repository;

import io.github.stephanieingrid.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
