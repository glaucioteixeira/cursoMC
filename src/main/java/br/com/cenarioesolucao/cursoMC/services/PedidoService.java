package br.com.cenarioesolucao.cursoMC.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.ItemPedido;
import br.com.cenarioesolucao.cursoMC.domains.PagamentoComBoleto;
import br.com.cenarioesolucao.cursoMC.domains.Pedido;
import br.com.cenarioesolucao.cursoMC.domains.enums.EstadoPagamento;
import br.com.cenarioesolucao.cursoMC.repositories.ItemPedidoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.PagamentoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.PedidoRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@Service
public class PedidoService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private EmailService emailService;
	
	
	public Pedido buscarId(Integer id) {
		Optional<Pedido> obj = pedidoRepo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Pedido.class.getName()));	
	}
	
	@Transactional
	public Pedido guardarEntidade(Pedido entity) {
		entity.setId(null);
		
		entity.setCliente(clienteService.buscarId(entity.getCliente().getId()));
		
		entity.setInstante(new Date());
		entity.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		entity.getPagamento().setPedido(entity);
		
		if (entity.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) entity.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, entity.getInstante());
		}
		
		entity = pedidoRepo.save(entity);
		pagamentoRepo.save(entity.getPagamento());
		
		for (ItemPedido itemPedido : entity.getItensPedidos()) {
			itemPedido.setDesconto(0.0);
			
			itemPedido.setProduto(produtoService.buscarId(itemPedido.getProduto().getId()));
			
			itemPedido.setPreco(itemPedido.getProduto().getPreco());
			itemPedido.setPedido(entity);
		}
		
		itemPedidoRepo.saveAll(entity.getItensPedidos());
		
		//System.out.println(entity);
		//emailService.emailConfirmacaoPedido(entity); // Envia em texto plano
		emailService.emailConfirmacaoPedidoHtml(entity); // Envia em HTML
		
		return entity;
	}
	
}
