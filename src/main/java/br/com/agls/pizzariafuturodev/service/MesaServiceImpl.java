package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.repository.MesaRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.MesaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Mesa atualizar(Long id, Mesa mesa) {
        Mesa mesaBuscada = this.buscar(id);
        BeanUtils.copyProperties(mesa, mesaBuscada,"id");
        return this.mesaRepository.save(mesaBuscada);
    }

    @Override
    public Mesa buscar(Long id) {
        return this.mesaRepository.findById(id).orElseThrow(() ->
        {throw  new EntityNotFoundException("Mesa com id " + id + " não encontrada!");
        });

    }

    @Override
    public List<Mesa> listar() {
        return mesaRepository.findAll();
    }

    @Override
    public List<Mesa> listarAtivas() {
//        List<Mesa> mesa = mesaRepository.findAll().stream().filter(mesa1 -> mesa1.getStatus() == true).collect(Collectors.toList());
        List<Mesa> mesa = mesaRepository.findAll();
        List<Mesa> mesasAtivas = new ArrayList<>();
        for (int i = 0; i < mesa.size(); i++) {
            if (Objects.nonNull(mesa.get(i).getStatus()))
                if (mesa.get(i).getStatus().equals(true)){
                    mesasAtivas.add(mesa.get(i));
                }
        }
//        for (Mesa mesas:mesa) {
//            if (mesas.getStatus() == true){
//                mesasAtivas.add(mesas);
//            }
//
//        }
        return mesasAtivas;
    }

    @Override
    public void excluir(Long id) {
        this.buscar(id);
        this.mesaRepository.deleteById(id);
    }
}
