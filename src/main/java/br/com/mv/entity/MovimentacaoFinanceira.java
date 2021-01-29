package br.com.mv.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.mv.enums.DebitoCreditoEnum;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
@Table(name = "MOVIMENTACAO_FINANCEIRA")
public class MovimentacaoFinanceira implements Serializable {

	private static final long serialVersionUID = -8687005769303546584L;
	
	@Id
	@Column(name = "ID_MOVIMENTACAO")
	@GeneratedValue(generator = "SEQ_MOVIMENTACAO_FINANCEIRA", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_MOVIMENTACAO_FINANCEIRA", sequenceName = "SEQ_MOVIMENTACAO_FINANCEIRA", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "DEBITO_CREDITO")
	private DebitoCreditoEnum debitoCredito;
	
	@Column(name = "DATA")
	private LocalDate data;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;
	
	@Column(name = "MOVIMENTACAO_INICIAL")
	private Boolean movimentacaoInicial;
	
}
