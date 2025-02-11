package com.diegoviana.bookworm_backend.service;

import com.diegoviana.bookworm_backend.model.User;
import com.diegoviana.bookworm_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository
                .findByEmail(user.getEmail())
                .isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        } else {
            return userRepository
                    .save(user);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
    }

    public User updateUser(Long userId, User updateUser) {
        User existingUser = getUserById(userId);
        existingUser
                .setUserName(updateUser
                        .getUserName());
        existingUser
                .setUserPicture(updateUser
                        .getUserPicture());
        return userRepository
                .save(existingUser);
    }

    public void deleteUser(Long userId) {
        userRepository
                .deleteById(userId);
    }
}
