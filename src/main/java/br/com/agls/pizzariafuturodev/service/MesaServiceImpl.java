package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.repository.MesaRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.MesaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Mesa atualizar(@PathVariable Long id, @RequestBody Mesa mesa) {
        Optional<Mesa> mesa1 = mesaRepository.findById(id);
        if (mesa1.isPresent()){
            BeanUtils.copyProperties(mesa, mesa1.get(),"id");
//            mesa.setId(mesa1.get().getId());
            return this.mesaRepository.save(mesa1.get());
        }
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
    public String excluir(Long id) {
        Optional<Mesa> mesa = this.mesaRepository.findById(id);
        if(mesa.isPresent()) {
            mesaRepository.delete(mesa.get());
            return "Excluido com sucesso";
        }
        return "Id não encontrado";
    }
}
