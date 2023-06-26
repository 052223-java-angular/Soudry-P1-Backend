package com.revature.DDWar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;


import com.revature.DDWar.dtos.requests.NewUserRequest;
import com.revature.DDWar.repositories.UserRepository;

import com.revature.DDWar.entities.User;

import org.junit.jupiter.api.Test;

public class UserServiceTests {

    @Mock
    private UserRepository userRepo;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepo);
    }

    @Test
    public void testRegisterUser() {
        String username = "testuser";
        String password = "password123";
        String confirmPassword = "password123";
        String email = "test@example.com";

        NewUserRequest newUserRequest = new NewUserRequest(username, password, confirmPassword, email);

        String hashedPassword = "$2a$10$R53hrXzg3DHzpX14g4Z8c.6C22x9IZYS9y4p36d8U8jc9MxLYNL7W";
        User savedUser = new User(username, hashedPassword, email);
        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        // Act
        User registeredUser = userService.registerUser(newUserRequest);

        // Assert
        assertNotNull(registeredUser);
        assertEquals(username, registeredUser.getUsername());
        assertEquals(hashedPassword, registeredUser.getPassword());
        assertEquals(email, registeredUser.getEmail());
        verify(userRepo, times(1)).save(any(User.class)); 
}
}