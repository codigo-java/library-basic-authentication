package br.com.codigojava.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codigojava.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
