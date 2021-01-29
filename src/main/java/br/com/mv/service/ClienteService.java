package br.com.mv.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.mv.autowireds.FabricaRelatorios;
import br.com.mv.dto.ClienteDto;
import br.com.mv.dto.ResultProcMovimentacao;
import br.com.mv.entity.Cliente;
import br.com.mv.exception.ApiMvException;
import br.com.mv.repository.ClienteRepository;
import br.com.mv.util.ConstantsMv;

@Service
public class ClienteService {
	private final Logger log = LoggerFactory.getLogger(ClienteService.class);


	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// configurado no br.com.mv.configuration.BaseConfiguration //
	@Autowired
	private FabricaRelatorios fabricaRelatorios;
	
	
	
	
	
	@Transactional
	public void removerCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException());
		clienteRepository.delete(cliente);
	}
	
	@Transactional
	public List<ClienteDto> buscarTodos(Integer currentPage) {
		currentPage = currentPage - 1;
		List<ClienteDto> dtos = new ArrayList<>();
		clienteRepository.buscaClientePag(PageRequest.of(currentPage, ConstantsMv.DEFAULT_PAGINAS))
		.stream().forEach((cliente) -> dtos.add(converterParaClienteDto(cliente)));
		return dtos;
	}

	@Transactional
	public void inserirCliente(ClienteDto cliente) {
		//ParameterMode.REF_CURSOR;
		log.info("VALIDANDO CPF/CNPJ");
		if((cliente.getCpf() == null ||  cliente.getCpf().isEmpty())  && (cliente.getCnpj() == null ||  cliente.getCnpj().isEmpty()) ){
			throw new ApiMvException("CNPJ ou CPF São Obrigatório");
		}else {
			if(cliente.getTipoCliente().equalsIgnoreCase(ConstantsMv.PF)) {
				cliente.setCnpj(null);
			}else if(cliente.getTipoCliente().equalsIgnoreCase(ConstantsMv.PJ)){
				cliente.setCpf(null);
			}else {
				throw new ApiMvException("Tipo Cliente Inválido");
			}
		}
		clienteRepository.save(converterParaClienteEntity(cliente));
	}
	
	@Transactional
	public Optional<ClienteDto> buscarPorId(Long id) {
		return clienteRepository.findById(id).map(cliente -> converterParaClienteDto(cliente));
	}
	

	@Transactional
	public List<ResultProcMovimentacao> buscaMovimentacaoClienteParaRelatorio (Integer idCliente, String dataInicio , String dataFim) {
		return geraResultProcMovimentacao( idCliente,  dataInicio ,  dataFim);
	}
	
	
	@Transactional
	public void downloadClienteMovimentacaoPeriodo(Integer idCliente, String dataInicio , String dataFim, HttpServletResponse response) {
		List<ResultProcMovimentacao> lista = geraResultProcMovimentacao( idCliente,  dataInicio ,  dataFim);

		if (CollectionUtils.isEmpty(lista)) {
			throw new ApiMvException("Sem registro(s)");
		}

		String nomePlanilha = "MovimentacaoCliente.xls";
		response.reset();
		response.setHeader("content-disposition", "attachment; filename=" + nomePlanilha);
		response.setContentType("application/xls");

		final HSSFWorkbook workbook = new HSSFWorkbook();

		fabricaRelatorios.preencherPlanilha(nomePlanilha, workbook, lista);

		try {
			workbook.write(response.getOutputStream());
			response.flushBuffer();
			response.getOutputStream().flush();
			workbook.close();
			response.getOutputStream().close();
		} catch (IOException e) {
			throw new ApiMvException(e);
		}
	}
	
	
	
	
	private List<ResultProcMovimentacao> geraResultProcMovimentacao (Integer idCliente, String dataInicio , String dataFim) {
		ResultSet rs =  clienteRepository.buscaClientePorPeriodoId(idCliente, dataInicio, dataFim);
		List<ResultProcMovimentacao> lista = new ArrayList<ResultProcMovimentacao>();
		try {
			while(rs.next()) {
				ResultProcMovimentacao dto = new ResultProcMovimentacao();
				dto.setId_conta(rs.getBigDecimal (1));
				dto.setId_movimentacao(rs.getBigDecimal(2));
				dto.setData(rs.getTimestamp(3)); 
				dto.setId_empresa(rs.getBigDecimal(4)); 
				dto.setMovimentacao_inicio(rs.getBigDecimal(5)); 
				dto.setValor(rs.getBigDecimal(6));
				lista.add(dto);
			}
		} catch (SQLException e) {
			throw new ApiMvException("Erro buscaMovimentacaoClienteParaRelatorio:" + ClienteService.class.getCanonicalName(),
					e);
		}
		return lista;
		
	}
	

	private ClienteDto converterParaClienteDto(Cliente cliente) {
		ClienteDto dto = modelMapper.map(cliente, ClienteDto.class);
		return dto; 
	}
	
	private Cliente converterParaClienteEntity(ClienteDto cliente) {
		Cliente entity = modelMapper.map(cliente, Cliente.class);
		return entity; 
	}
	

}
