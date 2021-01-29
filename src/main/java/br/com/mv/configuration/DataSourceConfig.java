package br.com.mv.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SuppressWarnings("rawtypes")
@Configuration
public class DataSourceConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource() {

		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver"); // MUDAR PARA O PROPERTIES

		String username = environment.getProperty("spring.datasource.username"); //
		String password = environment.getProperty("spring.datasource.password");
		String urlConexao = environment.getProperty("spring.datasource.url");

		System.out.println("Conectando com usuário: " + username);
		System.out.println("senha começa com " + password.substring(0, 2));
		System.out.println("Endereço do banco: " + urlConexao);


		dataSourceBuilder.url(urlConexao);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password); //
		
		return dataSourceBuilder.build();
	}
}