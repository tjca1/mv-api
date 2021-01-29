package br.com.mv.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mv.autowireds.FabricaRelatorios;

@Configuration
public class BaseConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper   = new ModelMapper();
		modelMapper.getConfiguration()
		  .setFieldMatchingEnabled(true)
		  .setFieldAccessLevel(AccessLevel.PRIVATE);
	    return modelMapper;
	}
	
	@Bean
	public FabricaRelatorios fabricaRelatorios() {
		FabricaRelatorios fabricaRelatorios   = new FabricaRelatorios();
	    return fabricaRelatorios;
	}
	
}
