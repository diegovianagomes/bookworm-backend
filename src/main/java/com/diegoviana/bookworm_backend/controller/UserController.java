package com.diegoviana.bookworm_backend.controller;

import com.diegoviana.bookworm_backend.model.User;
import com.diegoviana.bookworm_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService
                        .createUser(user));
    }
    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> gerAllUsers() {
        return ResponseEntity
                .ok(userService
                        .getAllUsers());
    }
    // Update user by Id
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser( @PathVariable Long userId, @RequestBody User user) {
        return ResponseEntity
                .ok(userService
                        .updateUser(userId, user));
    }
    // Delete user by Id
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
