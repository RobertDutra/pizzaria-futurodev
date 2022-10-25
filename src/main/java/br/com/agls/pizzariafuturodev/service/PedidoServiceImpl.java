package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.entity.Pedido;
import br.com.agls.pizzariafuturodev.entity.Prato;
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
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);

        if (pedido.isEmpty()){
            throw  new EntityNotFoundException("Não foi possível encontrar um pedido com id " + id);
        }

        return pedido.get();
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

    public Pedido fecharConta(Long idPedido) {
        Pedido pedidoFechado = null;
        Pedido pedidoPesquisado = buscar(idPedido);
        Double valorConta = calcularValorConta(pedidoPesquisado.getPedidoPrato());
        pedidoPesquisado.setValor(valorConta);

        //TODO Implementar pagamento.
        pedidoPesquisado.setPago(true);

        try {
            pedidoFechado = this.pedidoRepository.save(pedidoPesquisado);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        atualizarMesa(pedidoPesquisado.getMesa());
        return pedidoFechado;
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
