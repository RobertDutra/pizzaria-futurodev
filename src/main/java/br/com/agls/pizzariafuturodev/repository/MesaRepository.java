package br.com.agls.pizzariafuturodev.repository;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>
{
//    void delete(Long id);
}
