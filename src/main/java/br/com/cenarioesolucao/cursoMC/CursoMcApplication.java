package br.com.cenarioesolucao.cursoMC;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.domains.Endereco;
import br.com.cenarioesolucao.cursoMC.domains.Estado;
import br.com.cenarioesolucao.cursoMC.domains.ItemPedido;
import br.com.cenarioesolucao.cursoMC.domains.Municipio;
import br.com.cenarioesolucao.cursoMC.domains.Pagamento;
import br.com.cenarioesolucao.cursoMC.domains.PagamentoComBoleto;
import br.com.cenarioesolucao.cursoMC.domains.PagamentoComCartao;
import br.com.cenarioesolucao.cursoMC.domains.Pedido;
import br.com.cenarioesolucao.cursoMC.domains.Produto;
import br.com.cenarioesolucao.cursoMC.domains.enums.EstadoPagamento;
import br.com.cenarioesolucao.cursoMC.domains.enums.TipoCliente;
import br.com.cenarioesolucao.cursoMC.repositories.CategoriaRepository;
import br.com.cenarioesolucao.cursoMC.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoMC.repositories.EnderecoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.EstadoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.ItemPedidoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.MunicipioRepository;
import br.com.cenarioesolucao.cursoMC.repositories.PagamentoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.PedidoRepository;
import br.com.cenarioesolucao.cursoMC.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private MunicipioRepository municipioRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Municipio mun1 = new Municipio(null, "Uberlandia", est1);
		Municipio mun2 = new Municipio(null, "São Paulo", est2);
		Municipio mun3 = new Municipio(null, "Campinas", est2);
		
		est1.getMunicipios().addAll(Arrays.asList(mun1));
		est2.getMunicipios().addAll(Arrays.asList(mun2, mun3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		municipioRepo.saveAll(Arrays.asList(mun1, mun2, mun3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, mun1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, mun2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItensPedidos().addAll(Arrays.asList(ip1, ip2));
		ped2.getItensPedidos().addAll(Arrays.asList(ip3));
		
		prod1.getItensPedidos().addAll(Arrays.asList(ip1));
		prod2.getItensPedidos().addAll(Arrays.asList(ip3));
		prod3.getItensPedidos().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}

