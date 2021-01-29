package br.com.mv.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mv.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("Select c from Cliente c Order By id Desc")
	List<Cliente> buscaClientePag(Pageable pageable);
	
	@Procedure(name = "procRelarotioClienteNaEntity")
	ResultSet buscaClientePorPeriodoId ( @Param("idCliente") Integer idCliente, @Param("inicio") String dataInicio , @Param("fim") String dataFim);
	
	
}
