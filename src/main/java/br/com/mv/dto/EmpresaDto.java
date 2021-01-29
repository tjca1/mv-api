package br.com.mv.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmpresaDto {
	
	//@ApiModelProperty(value = "Identificador do Empresa", required = true, example = "1")
	//private Long id;
	
	@ApiModelProperty(value = "Nome da Empresa", required = true, example = "Fleury LTDA")
	private String nome;
	
	@ApiModelProperty(value = "Nome Fantasia da Empresa", required = true, example = "Fleury inovacao")
	private String nomeFantasia;
	
	@ApiModelProperty(value = "CNPJ", required = true, example = "15342345352345")
	private String cnpj;
	
}
