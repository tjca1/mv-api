package br.com.mv.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;


@Data
@NoArgsConstructor
@Entity
@Table(name = "DADOS_PESSOAIS")
public class DadosPessoais implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_DADOS_PESSOAIS")
	@GeneratedValue(generator = "SEQ_DADOS_PESSOAIS", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_DADOS_PESSOAIS", sequenceName = "SEQ_DADOS_PESSOAIS", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "NOME")
	private String nome;
	
	@NotNull
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@NotNull
	@Size(max = 1)
	@Column(name = "ESTADO_CIVIL")
	private String estadoCivil;


}
