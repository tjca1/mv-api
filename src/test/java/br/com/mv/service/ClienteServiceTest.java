package br.com.mv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;

import br.com.mv.dto.ClienteDto;
import br.com.mv.entity.Cliente;
import br.com.mv.repository.ClienteRepository;
import br.com.mv.util.ConstantsMv;
import br.com.mv.util.EntityUtils;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ClienteServiceTest {

	@Injectable
	ClienteService clienteService;

	@Injectable
	ClienteRepository clienteRepository;
	
	@Injectable
	java.util.Optional<Cliente> op;
	
	@Injectable
	Cliente cliente;
	
	@Injectable
	ClienteDto clientedto;
	
	@Injectable
	Integer currentPage = 1;
	
	@Injectable
	Integer idCliente = 2;
	@Injectable
	String dataInicio = "1999";
	@Injectable
	String dataFim= "2022";
	
	
	@Injectable
	Long lonng;
	
	@Injectable
	List<ClienteDto> dtos;
	
	@Injectable
	HttpServletResponse response;
	
	
	@Mock
	public ClienteRepository getClienteRepository() {
		MockUp<ClienteRepository> clienteRepository =  new MockUp<ClienteRepository>() {
			@Mock
			public Optional<Cliente> findById(Long n){
				
				return op;
				
			}};
		
		return clienteRepository.getMockInstance();
	}
	
	
	@Mock
	public ClienteService getClienteService() {
		MockUp<ClienteService> clienteService =  new MockUp<ClienteService>() {
			@Mock
			public ClienteDto converterParaClienteDto(Cliente  cliente){
				
				return clientedto;
			}
			
		
		   };
		
		return clienteService.getMockInstance();
	}
	
	
	@Test
	public void removerClienteTest() {
	    Long id = 0L;
		mockEntityUtils(id);
		clienteService.removerCliente(lonng);
	}

	
	@Test
	public void buscarTodosTest() {
		currentPage = currentPage - 1;
		clienteService.buscarTodos(currentPage);
	}
	
	@Test
	public void inserirClienteTest() {
		clienteService.inserirCliente(clientedto);
	}
	
	@Test
	public void buscarPorIdTest() {
		clienteService.buscarPorId(lonng);
	}
	
	@Test
	public void buscaMovimentacaoClienteParaRelatorioTest() {
		clienteService.buscaMovimentacaoClienteParaRelatorio(idCliente, dataInicio, dataFim);
		//downloadClienteMovimentacaoPeriodoTest
		clienteService.downloadClienteMovimentacaoPeriodo(idCliente, dataInicio, dataFim, response);
	}
	

	
	
	@SuppressWarnings({  "unchecked", "rawtypes" })
	private void mockEntityUtils(long id) {
		new MockUp<EntityUtils>() {
			@Mock
			public <PK, T> List<PK> convertEntitiesToIds(List<T> entities) {

				List arrayList = new ArrayList();
				arrayList.add(id);
				return arrayList;

			}

		};
	}

}
