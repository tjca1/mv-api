package br.com.mv.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 3270476995690167340L;
	
	@Id
	@Column(name = "ID_ENDERECO")
	@GeneratedValue(generator = "SEQ_ENDERECO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEQ_ENDERECO", sequenceName = "SEQ_ENDERECO", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Size(max = 50)
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	@Size(max = 10)
	@Column(name = "CEP")
	private String cep;
	
	@Size(max = 100)
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Size(max = 10)
	@Column(name = "NUMERO")
	private String numero;
	
	@Size(max = 50)
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Size(max = 50)
	@Column(name = "CIDADE")
	private String cidade;
	
	@Size(max = 2)
	@Column(name = "UF")
	private String uf;
	
}
