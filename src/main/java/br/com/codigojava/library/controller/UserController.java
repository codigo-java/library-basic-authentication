package br.com.codigojava.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.codigojava.library.model.User;
import br.com.codigojava.library.service.security.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@ExceptionHandler({ RuntimeException.class })
	public void createNewUser(@RequestBody User user) {
		userService.registerNewUserAccount(user);
	}
}
