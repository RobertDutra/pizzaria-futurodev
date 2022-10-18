package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.repository.MesaRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public Mesa salvar(Mesa mesa) {
        return this.mesaRepository.save(mesa);
//        Mesa mesaSalvar = this.mesaRepository.save(mesa);
//        return mesaSalvar;
    }



    @Override
    public Mesa atualizar(Mesa mesa) {
        return null;
    }

    @Override
    public Mesa buscar(Long id) {
        Optional<Mesa> mesa = this.mesaRepository.findById(id);
        if (mesa.isPresent()){
            return mesa.get();
        }
        return null;
    }

    @Override
    public List<Mesa> Listar() {
        return mesaRepository.findAll();
    }

    @Override
    public void excluir(Long id) {

    }
}
