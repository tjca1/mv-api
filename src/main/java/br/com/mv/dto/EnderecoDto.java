package br.com.mv.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mv.entity.Endereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDto {
	
	//private Long id;
	private String complemento;
	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
}
