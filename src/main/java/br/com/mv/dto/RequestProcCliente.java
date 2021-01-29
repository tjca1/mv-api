package br.com.mv.dto;



import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestProcCliente {
	
	@NotNull
	@ApiModelProperty(value = "Id Cliente para executar a procedure", required = true, example = "1")
	private Integer id;
	@NotNull
	@ApiModelProperty(value = "DataInicio Movimentacao para executar a procedure", required = true, example = "DD/MM/YYYY")
    private  String dInicio;
	@NotNull
	@ApiModelProperty(value = "DataFim Movimentacao para executar a procedure", required = true, example = "DD/MM/YYYY")
    private String dFim;
	
}
