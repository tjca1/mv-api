package br.com.mv.dto;


import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultProcMovimentacao {
	private BigDecimal id_conta;
	private BigDecimal id_movimentacao;
	private Timestamp  data; 
	private BigDecimal id_empresa; 
	private BigDecimal movimentacao_inicio; 
	private BigDecimal valor; 
	
	

}
