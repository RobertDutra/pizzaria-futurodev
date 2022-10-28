package br.com.agls.pizzariafuturodev.dto;

import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.entity.Cliente;
import br.com.agls.pizzariafuturodev.entity.TipoCartao;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartaoDto {

    @Max(16)
    private String numero;

    @NotNull
    private TipoCartao tipoCartao;

    @NotNull
    private LocalDate validade;

    @PositiveOrZero
    private Double limite;

}
