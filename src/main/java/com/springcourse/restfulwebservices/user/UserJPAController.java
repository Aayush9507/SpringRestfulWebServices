package com.springcourse.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;
	
//	get all users
	
//	get one user
	@GetMapping("jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
				
	}
	
	@GetMapping("jpa/users/{id}")
	public Resource<User> getuser(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
		
	}
	
	@DeleteMapping("jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		userRepository.deleteById(id);

		
	}
	
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saved = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
