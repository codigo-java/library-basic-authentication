package br.com.codigojava.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codigojava.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

}
