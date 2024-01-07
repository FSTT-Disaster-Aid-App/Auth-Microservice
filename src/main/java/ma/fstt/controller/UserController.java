package ma.fstt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.fstt.service.JwtService;

/**
 * UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	JwtService jwtService;

	// get username from token
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<Map<String, Object>> GetUsername(@RequestParam("token") String token) {
		try {
			String username = jwtService.getUserNameFromToken(token);
			return ResponseEntity.ok(Map.of("username", username));
		} catch (Exception e) {
			Map<String, Object> response = Map.of("status", "error", "message", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
	}
}
