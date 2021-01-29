package br.com.mv.entity;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "CONTA_PF_PJ")
public class Conta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CONTA")
	@GeneratedValue(generator = "SEQ_CONTA_PF_PJ", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_CONTA_PF_PJ", sequenceName = "SEQ_CONTA_PF_PJ", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Size(max = 20)
	@Column(name = "NUMERO_CONTA_PF_PJ")
	private String numeroConta;
	
	@Column(name = "STATUS_CONTA")
	private Boolean status;
	
	@Column(name = "SALDO")
	private BigDecimal saldo;
	
	@Size(max = 15)
	@Column(name = "AGENCIA")
	private String agencia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENTE" )
	private Cliente cliente;
	
	@ElementCollection(fetch = FetchType.LAZY)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CONTA" )
	private List<MovimentacaoFinanceira> movimentacaosFinanceiras;
	
}
