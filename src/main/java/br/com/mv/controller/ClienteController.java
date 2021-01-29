package br.com.mv.controller;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.context.ContextoConstants;
import br.com.mv.dto.ClienteDto;
import br.com.mv.dto.RequestProcCliente;
import br.com.mv.dto.ResultProcMovimentacao;
import br.com.mv.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(value = "/api/cliente")
@RestController
@RequestMapping("/api/cliente")
class ClienteController {

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);
    
    @Autowired
    private ClienteService service;


    @ApiImplicitParams({
		@ApiImplicitParam(name = ContextoConstants.token_nao_obrigatorio_porhora, value = "User Token", required = false, dataType = "string", paramType = "header")})
    @GetMapping("/porId/{id}")
    ResponseEntity<ClienteDto> buscarClientePorId(@PathVariable Long id) {
    	log.info("/porId/{id}", id);
    	Optional<ClienteDto> resultado = service.buscarPorId(id);
        return resultado.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @ApiImplicitParams({
		@ApiImplicitParam(name = ContextoConstants.token_nao_obrigatorio_porhora, value = "User Token", required = false, dataType = "string", paramType = "header")})
    @GetMapping("/todos")
    ResponseEntity<List<ClienteDto>> buscarTodos(@RequestParam(required = false) Integer pagina) {
    	log.info("/todos", "Busca todos os clientes paginados");
    	List<ClienteDto> resultado = service.buscarTodos(pagina);
        return ResponseEntity.ok().body(resultado);
    }
    
 
    
    @ApiImplicitParams({
    	@ApiImplicitParam(name = ContextoConstants.token_nao_obrigatorio_porhora, value = "User Token", required = false, dataType = "string", paramType = "header")})
    @PostMapping("/inserir")
    ResponseEntity<ClienteDto> inserir(@Valid @RequestBody ClienteDto dto) throws URISyntaxException{
    	log.info("inserir Cliente{}", dto);
    	service.inserirCliente(dto);
    	return ResponseEntity.created(new URI("/api/cliente/inserir" + dto.getStatusCliente() )).body(dto);
    }
    
    @ApiImplicitParams({
		@ApiImplicitParam(name = ContextoConstants.token_nao_obrigatorio_porhora, value = "User Token", required = false, dataType = "string", paramType = "header")})
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {
    	log.info("remove Cliente:", id);
        service.removerCliente(id);
        return ResponseEntity.ok().build();
    }
    
    
    @ApiImplicitParams({
		@ApiImplicitParam(name = ContextoConstants.token_nao_obrigatorio_porhora, value = "User Token", required = false, dataType = "string", paramType = "header")})
    @PostMapping("/procRelPorPeriodo")
    ResponseEntity <List<ResultProcMovimentacao>> buscaMovimentacaoClienteParaRelatorio(@Valid @RequestBody RequestProcCliente dto ) {
    	log.info("/procRelPorPeriodo :{}", dto);
    	List<ResultProcMovimentacao> lista =  service.buscaMovimentacaoClienteParaRelatorio(dto.getId(), dto.getDInicio(), dto.getDFim());
        return ResponseEntity.ok().body(lista);
    }
    
//    
// exec proc pelo sqlDevelop
//variable vtabelaCliente refcursor;
//DECLARE
//BEGIN
//ClientePorPeriodoId(:vtabelaCliente, 1, '01/01/2000' , '01/01/2022');
//END;
///
//print vtabelaCliente
//
//Body
//
//CREATE OR REPLACE 
//PROCEDURE ClientePorPeriodoId(
//cursor_ OUT SYS_REFCURSOR,
//idCliente IN INTEGER,
//inicio    IN   VARCHAR2,
//fim       IN VARCHAR2
//)
//AS
//BEGIN
//OPEN cursor_ FOR
//          select movimentacao.id_movimentacao as id_movimentacao, 
//          movimentacao.id_conta as id_conta, 
//          movimentacao.data as data, 
//          movimentacao.id_empresa as id_empresa, 
//          movimentacao.movimentacao_inicial as movimentacao_inicio, 
//          movimentacao.valor as valor from  movimentacao_financeira movimentacao 
//          inner join conta_pf_pj conta_ on movimentacao.id_conta=conta_.id_conta inner join 
//          cliente cliente_ on conta_.id_cliente=cliente_.id_cliente 
//          where (trunc(movimentacao.data) between 
//          TO_DATE( inicio , 'DD/MM/YYYY' ) 
//          and 
//          TO_DATE( fim, 'DD/MM/YYYY' ) 
//          ) 
//          and cliente_.id_cliente=idCliente;
//          DBMS_OUTPUT.PUT_LINE('EMPLOYEES WITH JOB ');
//END;
    
   
    
    

}