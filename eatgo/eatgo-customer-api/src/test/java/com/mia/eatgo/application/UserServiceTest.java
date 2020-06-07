package com.mia.eatgo.application;

import com.mia.eatgo.domain.EmailExistedException;
import com.mia.eatgo.domain.User;
import com.mia.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser(){
        String email = "tester@example.com";
        String name = "tester";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());


    }

    @Test(expected= EmailExistedException.class)
    public void registerUserWithExistedEmail(){
        String email = "tester@example.com";
        String name = "tester";
        String password = "test";

        User mockUser = User.builder().email(email).name(name).password(password).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
        userService.registerUser(email, name, password);

        verify(userRepository, never()).save(any());


    }

}