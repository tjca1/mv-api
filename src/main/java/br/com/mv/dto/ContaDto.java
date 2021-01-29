package br.com.mv.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContaDto {
	private String numeroConta;
	private Boolean status;
	private BigDecimal saldo;
	private String agencia;
	
	@NotNull
	private List<MovimentacaoFinanceiraDto> movimentacaosFinanceiras;

}
