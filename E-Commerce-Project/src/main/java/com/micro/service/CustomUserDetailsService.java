package com.micro.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.micro.model.CustomeUserDetail;
import com.micro.model.User;
import com.micro.repository.UserRepository;

public class CustomUserDetailsService  implements UserDetailsService{

	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> user = userRepo.findUserByEmail(email);
		user.orElseThrow(()-> new UsernameNotFoundException("user is not present"));
		return user.map(CustomeUserDetail::new).get();
	}

}
