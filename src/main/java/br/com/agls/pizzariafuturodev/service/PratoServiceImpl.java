package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Prato;
import br.com.agls.pizzariafuturodev.repository.PratoRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.PratoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class PratoServiceImpl implements PratoService {

    @Autowired
    private PratoRepository pratoRepository;

    @Override
    public Prato salvar(Prato prato) {
        return this.pratoRepository.save(prato);
    }

    @Override
    public Prato atualizar(Long id, Prato prato) {

        Optional<Prato> pratoAtualizado = this.pratoRepository.findById(id);
        if (pratoAtualizado.isPresent()){
            BeanUtils.copyProperties(prato, pratoAtualizado.get(), "id");
            return this.pratoRepository.save(pratoAtualizado.get());
        }
        return null;
    }

    @Override
    public Prato buscar(Long id) {
        Optional<Prato> pratoPesquisado = this.pratoRepository.findById(id);
        if (pratoPesquisado.isEmpty()){
            throw new EntityNotFoundException("Não foi possível encontrar um prato com id " + id);
        }
        return pratoPesquisado.get();
    }

    @Override
    public Prato buscarPorNome(String nome) {
        return  this.pratoRepository.findByNome(nome).orElseThrow(() -> {
            throw  new EntityNotFoundException("Não foi possível encontrar o prato com o nome " + nome);
        });
    }

    @Override
    public List<Prato> listar() {
        return this.pratoRepository.findAll();
    }

    @Override
    public String excluir(Long id) {
        Optional<Prato> prato = this.pratoRepository.findById(id);
        if (prato.isPresent()){
            pratoRepository.delete(prato.get());
            return "Prato excluído!";
        }
        return "Id " +  id + " não encontrado!";
    }
}