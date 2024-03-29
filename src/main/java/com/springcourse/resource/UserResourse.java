package com.springcourse.resource;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLogindto;
import com.springcourse.dto.UserUpdateRoledto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResourse {

	@Autowired
	private UserService userService;
	@Autowired
	private RequestService requestService;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user) {
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0")int page,
			@RequestParam(value = "size", defaultValue = "10")int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyModel(pr);
		return ResponseEntity.ok(pm);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLogindto user) {
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0")int page, @RequestParam(value = "size", defaultValue = "10")int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listByOwnerIdOnLazyModel(id, pr);
		return ResponseEntity.ok(pm);
	}
	
	@PatchMapping("/role/{id}")
	public ResponseEntity<?>updateRole(@PathVariable(name = "id") Long id ,@RequestBody UserUpdateRoledto userdto){
		User user = new User();
		user.setId(id);
		user.setRole(userdto.getRole());
		userService.updateRole(user);
		return ResponseEntity.ok().build();
	}
}
