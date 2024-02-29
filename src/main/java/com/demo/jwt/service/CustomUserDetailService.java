package com.demo.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.jwt.dao.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	
	  @Autowired UserRepo userRepo;
	 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.demo.jwt.model.User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());

		// logic to fetch user from db

		/*
		 * if (username.equals("abhishek")) { return new User("abhishek", "aaa", new
		 * ArrayList<>()); } else { throw new
		 * UsernameNotFoundException("user not found"); }
		 */
	}

}
