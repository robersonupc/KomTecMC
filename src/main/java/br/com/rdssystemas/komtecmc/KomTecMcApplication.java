package br.com.rdssystemas.komtecmc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rdssystemas.komtecmc.domain.Categoria;
import br.com.rdssystemas.komtecmc.domain.Cidade;
import br.com.rdssystemas.komtecmc.domain.Estado;
import br.com.rdssystemas.komtecmc.domain.Produto;
import br.com.rdssystemas.komtecmc.repositories.CategoriaRepository;
import br.com.rdssystemas.komtecmc.repositories.CidadeRepository;
import br.com.rdssystemas.komtecmc.repositories.EstadoRepository;
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
		
	}
	
	
}
