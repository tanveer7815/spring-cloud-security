package com.roytuts.spring.boot.auth.service.rest.controller;

import java.util.HashSet; 
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roytuts.spring.boot.auth.service.config.UserDetailsServiceImpl;
import com.roytuts.spring.boot.auth.service.dao.RoleDao;
import com.roytuts.spring.boot.auth.service.dao.UserDao;
import com.roytuts.spring.boot.auth.service.entity.Role;
import com.roytuts.spring.boot.auth.service.entity.User;
import com.roytuts.spring.boot.auth.service.util.JwtUtil;

@RestController
public class AuthRestController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/login")
	public ResponseEntity<String> login(@RequestBody User user) throws Exception {

//		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

//		String token = jwtUtil.generateToken(userName);

//		return new ResponseEntity<String>(token, HttpStatus.OK);
		
		String userName = user.getUserName();
		String password = user.getPassword();
		
		authenticate(userName, password);

//		if (userDetailsService.loadUserByUsername(userName) != null) {
//			String token = jwtUtil.generateToken(userName);
//			return new ResponseEntity<String>(token, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<String>("Invalid username ", HttpStatus.BAD_GATEWAY);
//		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		String generateToken = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<String>(generateToken, HttpStatus.OK);
		
		
	}
	
	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {

			throw new Exception("user is disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials from the user");
		}
	}

	@PostMapping("/auth/register")
	public ResponseEntity<User> register(@RequestBody User user) {

		Role role = roleDao.findById("User").get();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		user.setPassword(getEncodedPassword(user.getPassword()));

		User userEntity = userDao.save(user);
		if (userEntity != null) {
			return new ResponseEntity<User>(userEntity, HttpStatus.OK);
		}

		return new ResponseEntity<User>(userEntity, HttpStatus.BAD_REQUEST);

	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@PostConstruct
	public void initRolesAndUser() {

		Role adminRole = new Role();
		adminRole.setRollName("Admin");
		adminRole.setRoleDescription("Admin Roll");
		roleDao.save(adminRole);

		Role userRole = new Role();
		userRole.setRollName("User");
		userRole.setRoleDescription("Default Roll");
		roleDao.save(userRole);

		User adminUser = new User();
		adminUser.setUserName("tanveer");
		
		
		adminUser.setPassword(getEncodedPassword("tan@123"));

		Set<Role> roles = new HashSet<>();
		roles.add(adminRole);
		adminUser.setRole(roles);
		userDao.save(adminUser);
	}
	
	
}
