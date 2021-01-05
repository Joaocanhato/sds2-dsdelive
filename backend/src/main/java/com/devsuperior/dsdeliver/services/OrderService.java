package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;


@Service // Usado como serviço para injeção de componentes com a camade de repository
public class OrderService {
	
	@Autowired // Serve para que o Spring faça a resolução da dependência automaticamente
	private OrderRepository repository;
	
	@Transactional(readOnly = true) // Para que a operação não faça o lock de escrita no banco, otimizando o fluxo de trabalho.
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
}
