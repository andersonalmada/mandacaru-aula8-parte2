package br.ufc.mandacaru5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru5.model.Login;
import br.ufc.mandacaru5.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Login")
@RestController
@RequestMapping(path = "/api/login")
public class LoginController {

	@Autowired
	LoginService service;

	@PostMapping
	public ResponseEntity<Login> save(@RequestBody Login login) {
		return new ResponseEntity<Login>(service.save(login), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Login>> findAll() {
		return new ResponseEntity<List<Login>>(service.findAll(), HttpStatus.OK);
	}
}