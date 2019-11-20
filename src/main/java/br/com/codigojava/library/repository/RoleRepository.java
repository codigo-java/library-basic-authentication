package br.com.codigojava.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codigojava.library.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findByName(String string);

}
