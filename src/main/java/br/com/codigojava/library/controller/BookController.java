package br.com.codigojava.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.codigojava.library.business.BookBusiness;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookBusiness bookBusiness;

	@PostMapping
	public void upload(@RequestParam MultipartFile file) {
		bookBusiness.salvarArquivo(file);
	}
}
