package br.com.agls.pizzariafuturodev.utils;

import br.com.agls.pizzariafuturodev.dto.ClienteDto;
import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.entity.Cliente;
import org.springframework.beans.BeanUtils;

public class ClienteMapper {
    public static Cliente dtoToEntity(ClienteDto clienteDto){
        var clienteEntity = new Cliente();
        BeanUtils.copyProperties(clienteDto, clienteEntity);
        return clienteEntity;
    }
}
