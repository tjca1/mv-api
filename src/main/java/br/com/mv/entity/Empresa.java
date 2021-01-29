package br.com.mv.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.SequenceGenerator;
import org.hibernate.validator.constraints.br.CNPJ;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPRESA")

public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_EMPRESA")
	@GeneratedValue(generator = "SEQ_EMPRESA", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_EMPRESA", sequenceName = "SEQ_EMPRESA", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "NOME")
	private String nome;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "NOME_FANTASIA")
	private String nomeFantasia;
	
	@NotBlank
	@CNPJ
	@Column(name = "CNPJ")
	private String cnpj;
}
