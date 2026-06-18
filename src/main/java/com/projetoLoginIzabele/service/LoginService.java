package com.projetoLoginIzabele.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoLoginIzabele.entity.Login;
import com.projetoLoginIzabele.repository.LoginRepository;

@Service
public class LoginService {

	private final LoginRepository loginRepository;

	@Autowired
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public List<Login> buscaTodosLogins() {
		return loginRepository.findAll();
	}

	public Login buscaLoginId(Long id) {
		Optional<Login> Login = loginRepository.findById(id);
		return Login.orElse(null);
	}

	public Login salvaAluno(Login Login) {
		return loginRepository.save(Login);
	}

	public Login alterarLogin(Long id, Login alterarL) {
		Optional<Login> existeLogin = loginRepository.findById(id);
		if (existeLogin.isPresent()) {
			alterarL.setId(id);
			return loginRepository.save(alterarL);
		}
		return null;
	}

	public boolean apagarLogin(Long id) {
		Optional<Login> existeLogin = loginRepository.findById(id);
		if (existeLogin.isPresent()) {
			loginRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Login authenticate(String username, String senha) {
		Login user = (Login) loginRepository.findByUsername(username);

		if (user != null && user.getPassword().equals(senha)) {
			return user;
		}
		return null;

	}
}
