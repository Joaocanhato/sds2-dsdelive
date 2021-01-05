package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;


@Service // Usado como serviço para injeção de componentes com a camade de repository
public class ProductService {
	
	@Autowired // Serve para que o Spring faça a resolução da dependência automaticamente
	private ProductRepository repository;
	
	@Transactional(readOnly = true) // Para que a operação não faça o lock de escrita no banco, otimizando o fluxo de trabalho.
	public List<ProductDTO> findAll(){
		List<Product> list = repository.findAllByOrderByNameAsc();
		
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
	
}
