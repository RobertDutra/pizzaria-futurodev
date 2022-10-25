package br.com.agls.pizzariafuturodev.dto;

import br.com.agls.pizzariafuturodev.entity.Cartao;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartaoDto {

    @Max(16)
    private String numero;

    @NotNull
    private LocalDate validade;

    @NotNull
    private Double limite;

//    public static Cartao criarCartao(){
//        return new Cartao().setNumero(criarCartao().getNumero());
//    }
}
