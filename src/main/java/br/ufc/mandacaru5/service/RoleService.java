package br.ufc.mandacaru5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru5.model.Login;
import br.ufc.mandacaru5.model.Role;
import br.ufc.mandacaru5.repository.LoginRepository;
import br.ufc.mandacaru5.repository.RoleRepository;
import ch.qos.logback.core.util.COWArrayList;

@Service
public class RoleService {

	@Autowired
	RoleRepository repository;
	
	@Autowired
	LoginRepository loginRepository;

	public void save(Role entity) {
		repository.save(entity);
	}
	
	public void addRole(int id, int userId) {
		Login login = loginRepository.findById(userId).get();
		Role role = repository.findById(id).get();			
		
		List<Role> roles = login.getRoles();
		
		if(roles != null) {
			roles.add(role);
		} else {
			roles = new ArrayList<Role>();
			roles.add(role);
		}
		
		login.setRoles(roles);
		loginRepository.save(login);
	}
	
	public List<Role> findAll() {
        return repository.findAll();
    }
}
