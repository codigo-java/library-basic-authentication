package br.com.codigojava.library.service.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.codigojava.library.model.Role;
import br.com.codigojava.library.model.User;
import br.com.codigojava.library.repository.RoleRepository;
import br.com.codigojava.library.repository.UserRepository;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			String ex = "Not found an account with that email adress: " + email;
			throw new RuntimeException(ex );
		}

		return new org.springframework.security.core.userdetails.User(
					user.getEmail(), 
					user.getPassword(),
					user.isEnabled(), 
					true, 
					true, 
					true, 
					getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER")))
				);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(roles);
	}

	private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
}