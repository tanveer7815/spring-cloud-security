//package com.roytuts.spring.boot.auth.service.config;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import com.roytuts.spring.boot.auth.service.entity.User;
//
//@Component
//public class CustomUserDetails implements UserDetails {
//
//	private static final long serialVersionUID = 1L;
//
//	User user;
//
//	public CustomUserDetails(User user) {
//
//		this.user = user;
//	}
//
//	public CustomUserDetails() {
//
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//
//		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
//
//		return List.of(simpleGrantedAuthority);
//
//	}
//
//	@Override
//	public String getPassword() {
//
//		return user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return user.getUserName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
