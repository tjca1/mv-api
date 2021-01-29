package br.com.mv.controller;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.service.ClienteService;
import io.swagger.annotations.Api;


@Api(value = "/api/relatorios")
@RestController
@RequestMapping("/api/relatorios")
class RelatoriosController {
    
    @Autowired
    private ClienteService service;


    @RequestMapping(value = "/downloads/{idCliente}/{dataInicio}/{dataFim}", method = RequestMethod.GET)
	public void agendasOperacao(@PathVariable Integer idCliente , @PathVariable String dataInicio,@PathVariable String dataFim , HttpServletResponse response) {
    	service.downloadClienteMovimentacaoPeriodo(idCliente,dataInicio,dataFim, response);
	}
    
    

}