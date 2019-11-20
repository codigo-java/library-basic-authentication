package br.com.codigojava.library;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.codigojava.library.model.Author;
import br.com.codigojava.library.model.Book;
import br.com.codigojava.library.model.Role;
import br.com.codigojava.library.model.User;
import br.com.codigojava.library.repository.AuthorRepository;
import br.com.codigojava.library.repository.BookRepository;
import br.com.codigojava.library.repository.RoleRepository;
import br.com.codigojava.library.repository.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@PostConstruct
	@Transactional
	public void createAuthorsAndBooks(){
		Author alberto = new Author();
		alberto.setName("Alberto");
		authorRepository.save(alberto);
		
		Book book = new Book();
		book.setTitle("Spring MVC");
		book.setDescription("Domine o principal framework web Java");
		book.setNumOfPages(237);
		
		book.add(alberto);
		
		bookRepository.save(book);		
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("Test");
		user.setPassword(passwordEncoder.encode("test"));
		user.setEmail("test@test.com");
		user.setRoles(Arrays.asList(adminRole));
		user.setEnabled(true);
		userRepository.save(user);

		alreadySetup = true;
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}
}
