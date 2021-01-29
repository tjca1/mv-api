package br.com.mv.dto;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDto {
	
	@NotNull
	@ApiModelProperty(value = "Escolha o Tipo do Cliente, obs se escolher PJ prenncha somente o CNPJ, PF o CPF", required = true, example = "PF")
    private String tipoCliente;
	
	@CPF
	private String cpf;
	
	@CNPJ
	private String cnpj;
	
	@ApiModelProperty(value = "Status do Cliente", required = true, example = "true")
	private Boolean statusCliente;
	
	@ApiModelProperty(value = "Numero do Cliente", required = true, example = "55115558-8989")
	private String numeroTelefone;
	
	@ApiModelProperty(value = "Email do Cliente", required = true, example = "mv@mv.com")
	private String email;
	
	@ApiModelProperty(value = "Data do Cliente", required = true, example = "dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;
	
	@NotNull
	private EnderecoDto endereco;
	
	@NotNull
	private EmpresaDto empresa;
	
	@NotNull
	List<ContaDto> contas;
	
	@NotNull
	private DadosPessoaisDto dadosPessoais;

}
