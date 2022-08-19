package br.ufc.mandacaru5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru5.model.Login;
import br.ufc.mandacaru5.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository repository;

	public Login save(Login entity) {
		entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
		return repository.save(entity);
	}
	
	public List<Login> findAll() {
        return repository.findAll();
    }
}
