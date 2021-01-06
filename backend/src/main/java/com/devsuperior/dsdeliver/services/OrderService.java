package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;


@Service // Usado como serviço para injeção de componentes com a camade de repository
public class OrderService {
	
	@Autowired // Serve para que o Spring faça a resolução da dependência automaticamente
	private OrderRepository repository;
	
	@Autowired 
	private ProductRepository productRepository; //Instancia o repositório de produtos para manter lógica de arquitetura
	
	@Transactional(readOnly = true) // Para que a operação não faça o lock de escrita no banco, otimizando o fluxo de trabalho.
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto){
		
		// Um novo pedido é instanciado já dando todos os argumentos de criação
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
								Instant.now(), OrderStatus.PENDING);
		
		// Varre todos os produtos no DTO de produtos
		for (ProductDTO p : dto.getProducts()) {
			
			// Instancia um produto sem acessar o banco de dados, criando uma entidade, salvando as associações de pedido na tabela n:m
			Product product  = productRepository.getOne(p.getId());
			
			// Adiciona-se então o produto instanciado na lista dos pedidos
			order.getProducts().add(product);
			
		}
		
		// Então será salvo o repositório no objeto
		order = repository.save(order);
		
		return new OrderDTO(order);
	}
}
