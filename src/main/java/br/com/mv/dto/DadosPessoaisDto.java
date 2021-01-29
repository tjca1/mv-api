package br.com.mv.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DadosPessoaisDto {

	//private Long id;
	private String nome;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String estadoCivil;
	
}
