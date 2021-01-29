package br.com.mv.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.mv.enums.DebitoCreditoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimentacaoFinanceiraDto {
	
	private DebitoCreditoEnum debitoCredito;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	private BigDecimal valor;
	//private ContaDto conta;
	//private EmpresaDto empresa;
	private Boolean movimentacaoInicial;

}
