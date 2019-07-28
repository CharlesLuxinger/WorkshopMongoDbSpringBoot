package com.example.workshopmongospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongospring.domain.User;
import com.example.workshopmongospring.dto.UserDTO;
import com.example.workshopmongospring.repository.UserRepository;
import com.example.workshopmongospring.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findByID(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não Encontrado!!!"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findByID(id);
		repo.deleteById(id);
	}

	public User update(User obj) {
		User newObj = findByID(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
