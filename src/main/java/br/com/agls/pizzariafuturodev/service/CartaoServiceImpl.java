package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.repository.CartaoRepository;
import br.com.agls.pizzariafuturodev.repository.CategoriaRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.CartaoInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoServiceImpl implements CartaoInterface {

    @Autowired
    CartaoRepository cartaoRepository;

    @Override
    public Cartao salvar(Cartao cartao) {
        return this.cartaoRepository.save(cartao);
    }

    @Override
    public Cartao atualizar(Long id, Cartao cartao) {
        Optional<Cartao> cartaoAtualizado = this.cartaoRepository.findById(id);
        if (!cartaoAtualizado.isPresent()){
            throw  new EntityNotFoundException("Não foi possível encontrar o cartao com o id " + id);
        }
        BeanUtils.copyProperties(cartao, cartaoAtualizado.get(), "id");
        return this.cartaoRepository.save(cartaoAtualizado.get());
    }

    @Override
    public Cartao buscar(Long id) {
        return this.cartaoRepository.findById(id).orElseThrow(() -> {
            throw  new EntityNotFoundException("Não foi possível encontrar um cartão com o id " + id);
        });
    }

    @Override
    public List<Cartao> listar() {
        return this.cartaoRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        Optional<Cartao> cartao = this.cartaoRepository.findById(id);
        if (cartao.isPresent()){
            this.cartaoRepository.delete(cartao.get());
        }
    }

    @Override
    public Cartao buscarPeloNumeroCartaoClienteId(String numero, Long idCliente) {
        return this.cartaoRepository.findByNumeroAndClienteId(numero,idCliente).orElseThrow(() -> {
            throw new EntityNotFoundException("Não foi possível encontrar um cartão de número " + numero + " vinculado do cliente " + idCliente);
        } );
    }
}
