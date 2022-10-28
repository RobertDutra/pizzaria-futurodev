package br.com.agls.pizzariafuturodev.service.interfaces;

import br.com.agls.pizzariafuturodev.entity.Pedido;
import br.com.agls.pizzariafuturodev.entity.Prato;

import java.util.List;

public interface PedidoService {

    public Pedido salvar(Pedido pedido);
    public Pedido atualizar(Long id,Pedido pedido);
    public List<Pedido> listar();
    public Pedido buscar(Long id);
    public String deletar(Long id);
    public Pedido fecharConta(Long idPedido, String numeroCartao);

}
