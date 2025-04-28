package com.example.Braclet.Controlleur;

import com.example.Braclet.Entity.MyAppUser;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/req")
public class RegistrationController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    // ✅ User Registration (Signup)
    @PostMapping(value = "/signup", consumes = "application/json")
    public ResponseEntity<Object> createUser(@RequestBody MyAppUser user) {
        Map<String, String> response = new HashMap<>();

        // Check if username or email already exists
        boolean usernameExists = myAppUserRepository.findByUsername(user.getUsername()).isPresent();
        boolean emailExists = myAppUserRepository.findByEmail(user.getEmail()).isPresent();

        if (usernameExists || emailExists) {
            response.put("error", "This email or username is already in use.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBraceletid("0");
        // Save the new user
        myAppUserRepository.save(user);

        response.put("message", "User registered successfully!");
        response.put("id", String.valueOf(user.getId()));  // Add this line
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    // ✅ User Login (Signin) with JWT
    @PostMapping(value = "/signin", consumes = "application/json")
    public ResponseEntity<Object> loginUser(@RequestBody Map<String, String> loginRequest) {
        Map<String, Object> response = new HashMap<>();
        String usernameOrEmail = loginRequest.get("usernameOrEmail");
        String password = loginRequest.get("password");

        // Try to find by username or email
        Optional<MyAppUser> userOpt = myAppUserRepository.findByUsername(usernameOrEmail);
        if (!userOpt.isPresent()) {
            userOpt = myAppUserRepository.findByEmail(usernameOrEmail);
        }

        if (!userOpt.isPresent()) {
            response.put("error", "Invalid username/email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        MyAppUser user = userOpt.get();

        // Check if password matches
        if (!passwordEncoder.matches(password, user.getPassword())) {
            response.put("error", "Invalid username/email or password.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT Token
        String jwt = jwtUtils.generateToken(user);

        // Put all response data in the existing response map
        response.put("token", jwt);
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("status", "success");

        return ResponseEntity.ok(response);
    }
    // ✅ Update Bracelet ID for a User
    @Transactional
    @PutMapping(value = "/addBraceletId", consumes = "application/json")
    public ResponseEntity<Object> addBraceletId(@RequestBody Map<String, String> request) {
        System.out.println("Received request to update bracelet ID: " + request);
        Map<String, String> response = new HashMap<>();

        String username = request.get("username");
        String braceletid = request.get("braceletid");

        Optional<MyAppUser> userOpt = myAppUserRepository.findByUsername(username);

        if (!userOpt.isPresent()) {
            response.put("error", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        MyAppUser user = userOpt.get();
        user.setBraceletid(braceletid);
        myAppUserRepository.save(user);

        response.put("message", "Bracelet ID updated successfully.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }



    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        String email = request.get("email");

        Optional<MyAppUser> userOpt = myAppUserRepository.findByEmail(email);

        if (!userOpt.isPresent()) {
            response.put("message", "If this email exists, a reset link has been sent.");
            System.out.println("Response: " + response);  // Log the response for debugging
            return ResponseEntity.ok(response);  // always return 200 to avoid email discovery
        }

        MyAppUser user = userOpt.get();

        // Here you would normally generate a token and send an email.
        // For demo, print the token or reset link.
        String token = "dummy-reset-token"; // Replace with real token generation.
        System.out.println("Password reset link for " + email + ": http://your-app/reset?token=" + token);

        response.put("message", "If this email exists, a reset link has been sent.");
        System.out.println("Response: " + response);  // Log the response for debugging
        return ResponseEntity.ok(response);
    }



    @GetMapping("/users/bracelet")
    public ResponseEntity<Object> getBraceletIdByEmail(@RequestParam String email) {
        Optional<MyAppUser> userOpt = myAppUserRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("braceletId", userOpt.get().getBraceletid());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}