package br.com.rdssystemas.komtecmc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rdssystemas.komtecmc.domain.Categoria;
import br.com.rdssystemas.komtecmc.domain.Cidade;
import br.com.rdssystemas.komtecmc.domain.Cliente;
import br.com.rdssystemas.komtecmc.domain.Endereco;
import br.com.rdssystemas.komtecmc.domain.Estado;
import br.com.rdssystemas.komtecmc.domain.Pagamento;
import br.com.rdssystemas.komtecmc.domain.PagamentoComBoleto;
import br.com.rdssystemas.komtecmc.domain.PagamentoComCartao;
import br.com.rdssystemas.komtecmc.domain.Pedido;
import br.com.rdssystemas.komtecmc.domain.Produto;
import br.com.rdssystemas.komtecmc.domain.enums.EstadoPagamento;
import br.com.rdssystemas.komtecmc.domain.enums.TipoCliente;
import br.com.rdssystemas.komtecmc.repositories.CategoriaRepository;
import br.com.rdssystemas.komtecmc.repositories.CidadeRepository;
import br.com.rdssystemas.komtecmc.repositories.ClienteRepository;
import br.com.rdssystemas.komtecmc.repositories.EnderecoRepository;
import br.com.rdssystemas.komtecmc.repositories.EstadoRepository;
import br.com.rdssystemas.komtecmc.repositories.PagamentoRepository;
import br.com.rdssystemas.komtecmc.repositories.PedidoRepository;
import br.com.rdssystemas.komtecmc.repositories.ProdutoRepository;

@SpringBootApplication
public class KomTecMcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(KomTecMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Goiás", "GO");
		Estado est2 = new Estado(null, "Mato Grosso", "MT");
		Estado est3 = new Estado(null, "Minas Gerais", "MG");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2,p3));
		
		Cidade cid1 = new Cidade(null, "Goiânia", est1);
		Cidade cid2 = new Cidade(null, "Aparecida de Goiânia", est1);
		Cidade cid3 = new Cidade(null, "Anapolis", est1);
		Cidade cid4 = new Cidade(null, "Cuiabá", est2);
		Cidade cid5 = new Cidade(null, "Rondonópolis", est2);
		Cidade cid6 = new Cidade(null, "Belo Horizonte", est3);
		Cidade cid7 = new Cidade(null, "Uberlândia", est3);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid2, cid3));
		est2.getCidades().addAll(Arrays.asList(cid4, cid5));
		est3.getCidades().addAll(Arrays.asList(cid6, cid7));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2,est3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2,cid3, cid4, cid5, cid6, cid7));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912277", TipoCliente.FISICA);
		Cliente cli2 = new Cliente(null, "Marcelo Silva", "marcelo@gmail.com", "38398912288", TipoCliente.FISICA);
		Cliente cli3 = new Cliente(null, "Tractorgyn", "contato@tractorgyn.com.br", "36378912000177", TipoCliente.JURIDICA);
		Cliente cli4 = new Cliente(null, "Alvicto Ozores Nogueira & Cia", "pecas@kkmaquinas.com.br", "43378912000121", TipoCliente.JURIDICA);
		
		cli1.getTelefones().addAll(Arrays.asList("6232696601", "62981506090"));
		cli2.getTelefones().addAll(Arrays.asList("6232396032", "62981507723"));
		cli3.getTelefones().addAll(Arrays.asList("6232696601", "62981506090"));
		cli4.getTelefones().addAll(Arrays.asList("6232696601", "62981506090"));
		
		Endereco e1 = new Endereco(null, "Rua JC305", "7A", "Quadra 21 Condominio Lotus", "Jardim do Cerrado 7", "74491607", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua JC305", "12B", "Quadra 22 Condominio Lilas", "Jardim do Cerrado 7", "74491607", cli2, cid1);
		Endereco e3 = new Endereco(null, "Av. Perimetral Norte", "1532", "Próximo a GO080", "Santa Genoveva 2", "74675027", cli3, cid1);
		Endereco e4 = new Endereco(null, "Av. São Francisco", "1541", "Próximo ao areoporto", "Santa Genoveva", "74591007", cli4, cid1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1));
		cli2.getEnderecos().addAll(Arrays.asList(e2));
		cli3.getEnderecos().addAll(Arrays.asList(e3));
		cli4.getEnderecos().addAll(Arrays.asList(e4));
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("18/03/2018 08:03"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("18/03/2018 08:06"), cli2, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	    ped1.setPagamento(pgto1);
	    
	    Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("25/03/2018 00:00"), null);
	    ped2.setPagamento(pgto2);
	    
	    cli1.getPedidos().addAll(Arrays.asList(ped1));
	    cli2.getPedidos().addAll(Arrays.asList(ped2));
	    
	    pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
	    pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		
		
	}
	
	
}
