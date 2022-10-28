package br.com.agls.pizzariafuturodev.utils;

import br.com.agls.pizzariafuturodev.dto.CartaoDto;
import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.entity.Cliente;
import org.springframework.beans.BeanUtils;

public class CartaoMapper {

    public static Cartao dtoToEntity(CartaoDto cartaoDto){
        var cartaoEntity = new Cartao();
        BeanUtils.copyProperties(cartaoDto, cartaoEntity);
        cartaoEntity.setLimiteUtilizado(cartaoEntity.getLimiteUtilizado());
        cartaoEntity.setSaldo(cartaoEntity.getSaldo());
        cartaoEntity.setCliente(Cliente.builder().build());
        return cartaoEntity;
    }
}
