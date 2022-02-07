package com.roytuts.spring.boot.auth.service.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.roytuts.spring.boot.auth.service.dao.UserDao;
import com.roytuts.spring.boot.auth.service.entity.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDao.getUserName(username);

		if (user != null) {
			// throw new UsernameNotFoundException("could not found user");
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					getAuthorities(user));
		} else {

			throw new UsernameNotFoundException("username is not valid");
		}

		// CustomUserDetails customUserDetails = new CustomUserDetails(user);

//		return customUserDetails;
	}

	private Set getAuthorities(User user) {
		Set authorities = new HashSet();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRollName()));
		//	authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRollName()));
		});
		return authorities;
	}

}
