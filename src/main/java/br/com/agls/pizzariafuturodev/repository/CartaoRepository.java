package br.com.agls.pizzariafuturodev.repository;

import br.com.agls.pizzariafuturodev.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
