package br.com.agls.pizzariafuturodev.utils;

import br.com.agls.pizzariafuturodev.dto.CartaoDto;
import br.com.agls.pizzariafuturodev.entity.Cartao;
import org.springframework.beans.BeanUtils;

public class CartaoMapper {

    public static Cartao dtoToEntity(CartaoDto cartaoDto){
        var cartaoEntity = new Cartao();
        BeanUtils.copyProperties(cartaoDto, cartaoEntity);
        cartaoEntity.setLimiteUtilizado(cartaoEntity.getLimiteUtilizado());
        return cartaoEntity;
    }
}
