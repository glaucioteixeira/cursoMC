package br.com.cenarioesolucao.cursoMC.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.domains.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	/* Classe da camada de acesso aos dados. Basta a anotação @Repository e a assinatura da interface
	 * Ao extender JpaRepository é passado como tipo a classe de dominio já mapeada (JPA) e o tipo do 
	 * atributo identificador do objeto, que no cado é o Integer (id). 
	 */
	
	@Transactional()
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageable);
	
}
