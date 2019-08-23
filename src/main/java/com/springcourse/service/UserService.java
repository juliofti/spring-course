package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	public User update(User user) {
		User updateUser = userRepository.save(user);
		return updateUser;
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
			return result.get();
	}
	
	public List<User> lisAll(){
		List<User> users = userRepository.findAll();
		return  users;
	}
	
	public User login(String email, String password) {
		Optional<User> result = userRepository.login(email, password);
		return result.get();
	}
}
