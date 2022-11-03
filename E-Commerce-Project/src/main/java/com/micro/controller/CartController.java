package com.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.global.GlobalData;
import com.micro.model.Product;
import com.micro.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("addToCart/{id}")
	public String addCarts(@PathVariable Long id) {
		
		GlobalData.cart.add(productService.getProductsById(id).get());
		
		
		
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String removCartItems(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkOut(Model model) {
		
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}

}
