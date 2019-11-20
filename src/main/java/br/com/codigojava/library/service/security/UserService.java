package br.com.codigojava.library.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.codigojava.library.business.UserBusiness;
import br.com.codigojava.library.model.User;

@Service
public class UserService {

	@Autowired
	private UserBusiness userBusiness;

	public void registerNewUserAccount(User user) {
		userBusiness.registerNewUserAccount(user);		
	}

}
