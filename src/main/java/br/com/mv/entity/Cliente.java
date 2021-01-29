package br.com.mv.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@SqlResultSetMapping(name = "mapProcRelarotioClienteNaEntity", classes = {
		 @ConstructorResult(targetClass = MovimentacaoFinanceira.class,
		 columns = { @ColumnResult(name = "id_conta")
		           })
		 })

@NamedStoredProcedureQuery(
	    name = "procRelarotioClienteNaEntity", 
	    procedureName = "ClientePorPeriodoId",
	    resultSetMappings = { "mapProcRelarotioClienteNaEntity" },
	    parameters = {
	        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = ResultSet.class, name = "cursor_"),	
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "idCliente"),
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "inicio"),
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "fim"),
	    }
	)



@Entity
@Table(name = "CLIENTE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CLIENTE")
	@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "SEQ_CLIENTE", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "STATUS_CLIENTE")
	private Boolean statusCliente;
	
	@Size(max = 2)
	@Column(name = "TIPO_CLIENTE")
    private String tipoCliente;
	
	@CPF
	@Column(name = "CPF")
	private String cpf;
	
	@CNPJ
	@Column(name = "CNPJ")
	private String cnpj;
	
	
	@Size(max = 15)
	@Column(name = "TELEFONE_CLIENTE")
    private String numeroTelefone;
	
	@Email
	@Size(max = 50)
	@Column(name = "EMAIL_CLIENTE")
	private String email;
	
	@Column(name = "DATA")
	private LocalDate data;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ENDERECO")
	private Endereco endereco;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CLIENTE")
	private List<Conta> contas;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_PESSOA")
	private DadosPessoais dadosPessoais;

	
}
