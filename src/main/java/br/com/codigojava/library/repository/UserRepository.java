package br.com.codigojava.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codigojava.library.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}
