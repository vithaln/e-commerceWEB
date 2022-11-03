package com.micro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.micro.global.GlobalData;
import com.micro.model.Role;
import com.micro.model.User;
import com.micro.repository.RoleRepository;
import com.micro.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private BCryptPasswordEncoder bcryptPassword;
	
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private RoleRepository roleRepo;
	@GetMapping("/login")
	public String login() {
		
		GlobalData.cart.clear();
		return"login";
	}


	@GetMapping("/register")
	public String register() {
		
		return"register";
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException {
		
		String password=user.getPassword();
		user.setPassword(bcryptPassword.encode(password));	
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		user.setRoles(roles);
		
		userrepo.save(user);
		
		request.login(user.getEmail(),password);
		return"redirect:/login";
	}

}
