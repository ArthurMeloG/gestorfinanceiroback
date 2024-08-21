package org.r2.gestorfinanceiro.repository;

import org.r2.gestorfinanceiro.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
