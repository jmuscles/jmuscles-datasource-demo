/**
 * 
 */
package com.jmuscles.datasource.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author manish goel
 *
 */
@RestController
public class DatasourceSpringBootDemoController {

	@Autowired
	private SQLQueryExecutor sqlQueryExecutor;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/write/{sqlKey}")
	public ResponseEntity<Integer> createUser(@RequestBody User user,
			@PathVariable(value = "sqlKey", required = true) String sqlKey) {
		int response = 0;
		if ("default".equals(sqlKey)) {
			userRepository.save(user);
			response = 1;
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			response = sqlQueryExecutor.write(objectMapper.convertValue(user, Map.class), sqlKey);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = { "/get/{sqlKey}", "/get/{sqlKey}/" })
	public ResponseEntity<List<User>> getUsers(@PathVariable(value = "sqlKey", required = true) String sqlKey) {
		List<User> response = null;
		if ("default".equals(sqlKey)) {
			response = userRepository.findAll();
		} else {
			response = sqlQueryExecutor.read(sqlKey);
		}
		return ResponseEntity.ok(response);
	}
}
