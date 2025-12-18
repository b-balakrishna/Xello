package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the provided email and password.
     *
     * @param email    The email of the user to be created.
     * @param password The password for the user.
     * @return The newly created User object.
     * @throws IllegalArgumentException if the email or password is null or empty.
     * @throws RuntimeException if a user with the same email already exists.
     */
    public User createUser(String email, String password) {
        // Check if email and password are not null and not empty
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password must not be null or empty");
        }

        // Check if the user with the given email already exists
        if (userRepository.exists(email)) {
            throw new RuntimeException("User with this email already exists");
        }

        // Create a new User object with the provided email and password.
        User newUser = new User(email, password);

        // Save the newly created User object to the repository.
        User user = userRepository.save(newUser);

        // Return the newly created User.
        return user;
    }
}
