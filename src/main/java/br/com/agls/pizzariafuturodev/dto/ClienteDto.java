package br.com.agls.pizzariafuturodev.dto;

import br.com.agls.pizzariafuturodev.entity.Cartao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    @NotNull
    private String nome;

    private List<CartaoDto> cartoes;
}
