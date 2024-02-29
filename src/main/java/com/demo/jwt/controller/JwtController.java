package com.demo.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jwt.model.JwtRequest;
import com.demo.jwt.model.JwtResponse;
import com.demo.jwt.service.CustomUserDetailService;
import com.demo.jwt.util.JwtUtils;

@RestController
public class JwtController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (UsernameNotFoundException e) {
			throw new Exception("Bad Credential");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credential");
		}
		UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUsername());
		String jwt = jwtUtils.generateToken(userDetails);
		JwtResponse jwtResponse = new JwtResponse(jwt);
		return ResponseEntity.ok(jwtResponse);
		
	}

}
