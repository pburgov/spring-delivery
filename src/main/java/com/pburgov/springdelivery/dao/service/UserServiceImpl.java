package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.Authority;
import com.pburgov.springdelivery.dao.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements  UserService {


	private UserRepository userRepository;


	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Transactional
	public com.pburgov.springdelivery.dao.entity.User findByUserName(String userName) {
		// check the database if the user already exists
		return userRepository.findByUsername(userName);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.pburgov.springdelivery.dao.entity.User tempUser = userRepository.findByUsername(username);

		if (tempUser == null) {
			throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Authority authority : tempUser.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}

		if (authorities.isEmpty()) {
			throw new UsernameNotFoundException(
					"Error en el Login: usuario '" + username + "' no tiene roles asignados!");
		}

		 UserDetails user = (UserDetails) new User(tempUser.getUsername(), tempUser.getPassword(), authorities);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, authorities);

	}
	


}
