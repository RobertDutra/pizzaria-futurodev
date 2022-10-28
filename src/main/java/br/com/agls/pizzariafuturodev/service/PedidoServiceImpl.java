package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.entity.Pedido;
import br.com.agls.pizzariafuturodev.entity.Prato;
import br.com.agls.pizzariafuturodev.excpetion.FalhaNoPagamentoException;
import br.com.agls.pizzariafuturodev.excpetion.SaldoInsuficienteException;
import br.com.agls.pizzariafuturodev.repository.PedidoRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.MesaService;
import br.com.agls.pizzariafuturodev.service.interfaces.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private CartaoServiceImpl cartaoService;

    @Override
    public Pedido salvar(Pedido pedido) {
        Pedido pedidoSalvo = this.pedidoRepository.save(pedido);

        if (pedidoSalvo.getId() != null ){
            Mesa mesaReservada = this.mesaService.buscar(pedidoSalvo.getMesa().getId());
            mesaReservada.setStatus(false);
            this.mesaService.atualizar(mesaReservada.getId(), mesaReservada);
        }
        return pedidoSalvo;
    }

    @Override
    public Pedido atualizar(Long id, Pedido pedido) {
        Pedido pedidoPesquisado = buscar(id);

        if (pedidoPesquisado != null){
            BeanUtils.copyProperties(pedido,pedidoPesquisado, "id", "pedidoPrato");
            return this.pedidoRepository.save(pedidoPesquisado);
        }

        return null;

    }

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido buscar(Long id) {
        return this.pedidoRepository.findById(id).orElseThrow(() -> {
            throw  new EntityNotFoundException("Não foi possível encontrar um pedido com id " + id);
        });
    }

    @Override
    public String deletar(Long id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);

        if (pedido.isPresent()){
            this.pedidoRepository.delete(pedido.get());
            return "Pedido excluído com sucesso!";
        }
        return "Id " + id + " não encontrado!";
    }

    @Override
    public Pedido fecharConta(Long idPedido, String numeroCartao) {
        Pedido pedidoFechado = null;
        Pedido pedidoPesquisado = this.buscar(idPedido);
        Double valorConta = calcularValorConta(pedidoPesquisado.getPedidoPrato());
        pedidoPesquisado.setValor(valorConta);

        //TODO Implementar pagamento.
        fazerPagamento(pedidoPesquisado, numeroCartao);

        try {
            pedidoFechado = this.pedidoRepository.save(pedidoPesquisado);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        atualizarMesa(pedidoPesquisado.getMesa());
        return pedidoFechado;
    }

    private void fazerPagamento(Pedido pedido, String numeroCartao){
        Double valorConta = pedido.getValor();
        Cartao cartaoSelecionado = this.cartaoService.buscarPeloNumeroCartaoClienteId(numeroCartao,pedido.getCliente().getId());

        cartaoSelecionado.setSaldo(cartaoSelecionado.getLimite());
        cartaoSelecionado.setLimiteUtilizado(0.0);
        if (cartaoSelecionado.getSaldo() >= valorConta){
            try {
                cartaoSelecionado.setLimiteUtilizado(cartaoSelecionado.getLimiteUtilizado() + valorConta);
                cartaoSelecionado.setSaldo(cartaoSelecionado.getLimite() - cartaoSelecionado.getLimiteUtilizado());
                this.cartaoService.atualizar(cartaoSelecionado.getId(), cartaoSelecionado);
                pedido.setPago(true);
            }catch (Exception e){
                throw  new FalhaNoPagamentoException();
            }
        }
        else {
            throw  new SaldoInsuficienteException();
        }
    }
    private Double calcularValorConta(List<Prato> pratosPedidos) {
        Double total = 0.0;

        if(pratosPedidos != null || !pratosPedidos.isEmpty()) {
            for (Prato prato: pratosPedidos) {
                total += prato.getValor();
            }
        }
        return total;
    }

    private void atualizarMesa(Mesa mesa) {
        Mesa mesaPesquisada = this.mesaService.buscar(mesa.getId());
        mesaPesquisada.setStatus(true);
        this.mesaService.atualizar(mesaPesquisada.getId(), mesaPesquisada);
    }
}
