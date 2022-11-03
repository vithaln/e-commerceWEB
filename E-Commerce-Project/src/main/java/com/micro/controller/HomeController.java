package com.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.global.GlobalData;
import com.micro.service.CategoryService;
import com.micro.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping({"/","/hmoe"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "index";
	}
	

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategoryId(Model model,@PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.getAllProductbycategoryId(id));
		model.addAttribute("cartCount", GlobalData.cart.size());

		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable Long id) {
		model.addAttribute("product", productService.getProductsById(id).get());
		return "viewProduct";
	}
	
	
	
	
	
	
	
}
