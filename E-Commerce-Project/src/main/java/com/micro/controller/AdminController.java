package com.micro.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.micro.DTO.productDto;
import com.micro.model.Category;
import com.micro.model.Product;
import com.micro.service.CategoryService;
import com.micro.service.ProductService;

@Controller
public class AdminController {
	
	
	//CATEGORY-SECTION
	
	
	@Autowired
	private CategoryService cateService;
	
	@GetMapping("/admin")
	public String adminPage() {
		
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", cateService.getAllCategories());
		
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getcategoriesAdd(Model model) {
		model.addAttribute("category", new Category());
		
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postcategoriesAdd(@ModelAttribute("category") Category category) {
		
		cateService.addCategorories(category);
		
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategoryById(@PathVariable int id) {
		
		 cateService.deleteCategoryByID(id);
		 return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategorories(@PathVariable int id,Model model) {
		Optional<Category> categoryById = cateService.getCategoryById(id);
		
		if(categoryById.isPresent()) {
			model.addAttribute("category", categoryById.get());
			return "categoriesAdd";
		}
		else {
			return "404";
		}
	}
	
	//PRODUCT-SECTION
	
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@Autowired
	private ProductService prodService;
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		
		model.addAttribute("products", prodService.getAllProducts());
		
		return "products";
	}
	
	//add products
	@GetMapping("/admin/products/add")
	public String addProducts(Model model) {
		
		model.addAttribute("productDTO", new productDto());
		model.addAttribute("categories", cateService.getAllCategories());
		
		return "productsAdd";
	}
	
	
	
	@PostMapping("/admin/products/add")
	public String productAddSave(@ModelAttribute("productDTO") productDto productdto,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName)throws IOException
	{
		Product product=new Product();
		product.setId(productdto.getId());
		product.setName(productdto.getName());
		product.setImageName(productdto.getImageName());
		product.setCategory(cateService.getCategoryById(productdto.getCategoryId()).get());
		product.setPrice(productdto.getPrice());
		product.setWeight(productdto.getWeight());
		product.setDescription(productdto.getDescription());
		
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath,file.getBytes());
		}
		else {
			imageUUID=imgName;
		}
		product.setImageName(imageUUID);
		prodService.addProducts(product);
		
		
		return "redirect:/admin/products";
	}
	
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProductById(@PathVariable Long id) {
		prodService.removeProductsbyId(id);
		 
		 return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProducts(@PathVariable Long id, Model model) {
		
	 Product product = prodService.getProductsById(id).get();
	 
	 productDto productdto=new productDto();
	 
	 productdto.setId(product.getId());
	 productdto.setName(product.getName());
	 productdto.setCategoryId(product.getCategory().getId());
	 productdto.setPrice(product.getPrice());
	 productdto.setDescription(product.getDescription());
	 productdto.setImageName(product.getImageName());
	 productdto.setWeight(product.getWeight());
	 

	 model.addAttribute("categories", cateService.getAllCategories());
	 model.addAttribute("productDTO",productdto);
	 
	 
	 
		return "productsAdd";
		
	}
	
	
	
	
	
	
	
}
