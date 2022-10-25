package br.com.agls.pizzariafuturodev.repository;

import br.com.agls.pizzariafuturodev.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long > {
}
