package com.micro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.model.Product;
import com.micro.repository.ProductRepo;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepo prodrepo;
	
	
	public List<Product> getAllProducts(){
		return prodrepo.findAll();
		
	}
	
	public void addProducts(Product product) {
		prodrepo.save(product);
	}
	
	public void removeProductsbyId(Long id) {
		prodrepo.deleteById(id);
	}
	
	public Optional<Product> getProductsById(Long id){
		return prodrepo.findById(id);
	}
	
	public List<Product> getAllProductbycategoryId(int id){
		
		return prodrepo.findAllByCategory_Id(id);
	}
	
	
	
	
}
