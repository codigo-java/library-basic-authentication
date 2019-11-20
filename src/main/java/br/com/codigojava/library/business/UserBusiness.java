package br.com.codigojava.library.business;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.codigojava.library.model.User;
import br.com.codigojava.library.repository.RoleRepository;
import br.com.codigojava.library.repository.UserRepository;

@Component
public class UserBusiness {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	public User registerNewUserAccount(User user) {
		if (user.getEmail().isEmpty() || user.getEmail() == null){
			throw new RuntimeException("There is an account with that email adress: " + user.getEmail());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		return userRepository.save(user);
	}
	
}
