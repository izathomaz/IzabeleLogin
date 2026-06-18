package com.projetoLoginIzabele.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoLoginIzabele.entity.Login;
import com.projetoLoginIzabele.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Login> buscaLoginControlId(@PathVariable Long id) {
		Login login = loginService.buscaLoginId(id);
		if (login != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/")
	public ResponseEntity<List<Login>> buscaTodosLoginsControl() {
		List<Login> Logins = loginService.buscaTodosLogins();
		return ResponseEntity.ok(Logins);
	}

	@PostMapping("/")
	public ResponseEntity<Login> salvaLoginsControl(@RequestBody @Valid Login login) {
		Login salvaLogin = loginService.salvaAluno(login);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaLogin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Login> alteraLoginControl(@PathVariable Long id, @RequestBody @Valid Login login) {
		Login alteraLogin = loginService.alterarLogin(id, login);
		if (alteraLogin != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Login> apagaLoginControl(@PathVariable Long id) {
		boolean apagar = loginService.apagarLogin(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/auth")
	public ResponseEntity<Login> authenticate(@RequestBody Login loginDetails) {
		Login authenticatedUser = loginService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());

		if (authenticatedUser != null) {
			authenticatedUser.setPassword(null);
			return ResponseEntity.ok(authenticatedUser);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}