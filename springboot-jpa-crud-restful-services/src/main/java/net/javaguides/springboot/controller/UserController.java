package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;


	@GetMapping("/hello")
	public String sum() {
		return "hello world";
	}
	
	@GetMapping("/hello1")
	public String sum1() {
		return "hello Anu";
	}
	
	// get all users
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		//List<User> userList = userRepository.findAll();
		User user = new User("Harsh", "Raj", "a@gmail");
		User user1 = new User("Anu", "Raj", "a@gmail");
		User user2 = new User("Anu", "Priya", "a@gmail");
		User user3 = new User("Kirti", "Raj", "a@gmail");
		User user4 = new User("Aarti", "Verma", "a@gmail");
		
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		userList.add(user4);
		
		return userList;
	}
	
	// get all users
	@GetMapping("/getAllUsersfromDB-jpa")
	public List<User> getAllUsers1() {
	List<User> userList = userRepository.findAll();
	return userList;
	}

	// get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// create user
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	// update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
		 User existingUser = this.userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 existingUser.setFirstName(user.getFirstName());
		 existingUser.setLastName(user.getLastName());
		 existingUser.setEmail(user.getEmail());
		 return this.userRepository.save(existingUser);
	}
	
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		 User existingUser = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.userRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}

	@GetMapping("/fetchEmployeeJDBCData")
	public String fetchEmployeeData(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("User-Agent", "Application");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response=restTemplate.exchange("http://localhost:8003/emp/v1/getAllEmp", HttpMethod.GET, entity, String.class);
		System.out.println(response);

		return response.getBody();
	}

	@GetMapping(value = "/productList")
	public String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("User-Agent", "Application");
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response=restTemplate.exchange("https://catfact.ninja/fact", HttpMethod.GET, entity, String.class);
		System.out.println(response);
		return response.getBody();
	}

}
