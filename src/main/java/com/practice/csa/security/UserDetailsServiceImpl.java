package com.practice.csa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.practice.csa.exception.UsernameNotFoundException;
import com.practice.csa.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		
		return userRepository.findByEmail(username)
				.map(user -> new UserDetailsImpl(user))
				.orElseThrow(() -> new UsernameNotFoundException("User Email not found"));
	}

	
}
