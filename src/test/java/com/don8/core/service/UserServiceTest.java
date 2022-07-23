//package com.don8.core.service;
//
//import com.don8.core.model.dbentity.User;
//import com.don8.core.model.exception.ResourceNotFoundException;
//import com.don8.core.model.request.JwtRequest;
//import com.don8.core.port.outbound.UserRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.validation.ValidationException;
//import java.math.BigInteger;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserServiceTest {
//    @InjectMocks
//    UserService userService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    PasswordEncoder encoder;
//
//    @Mock
//    ObjectMapper objectMapper;
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    void getUser() {
//        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(null));
//        when(userRepository.findByEmail("asdf@gmail.com")).thenReturn(Optional.of(User.builder().build()));
//
//        User user = userService.getUser("asdf@gmail.com");
//        assertThat(user).isNotNull();
//        user = userService.getUser("random@gmail.com");
//        assertThat(user).isNull();
//    }
//
//    @Test
//    void storeUser() throws JsonProcessingException {
//        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(null));
//        when(userRepository.findByEmail("asdf@gmail.com")).thenReturn(Optional.of(User.builder().build()));
//        final User user = User.builder()
//                .email("asdf@gmail.com")
//                .name("test user")
//                .phone(new BigInteger("1233444698"))
//                .password("asdf1234")
//                .build();
//
//        assertThatThrownBy(() -> userService.storeUser(user))
//                .isInstanceOf(ValidationException.class);
//
//
//
//        User validUser = User.builder()
//                .email("asdf1@gmail.com")
//                .name("test user")
//                .phone(new BigInteger("1233444698"))
//                .password("asdf1234")
//                .build();
//        when(userRepository.save(validUser)).thenReturn(validUser);
//        assertThat(userService.storeUser(validUser)).isNotNull();
//    }
//
//    @Test
//    void resetPassword() {
//        when(userRepository.findByEmail(any())).thenReturn(Optional.ofNullable(null));
//        when(userRepository.findByEmail("asdf@gmail.com")).thenReturn(Optional.of(User.builder().build()));
//        JwtRequest jwtRequest = new JwtRequest("random@gmail.com", "asdf1234");
//        assertThat(userService.resetPassword(jwtRequest)).isFalse();
//
//        when(userRepository.save(any())).thenReturn(User.builder().build());
//        jwtRequest = new JwtRequest("asdf@gmail.com", "asdf1234");
//        assertThat(userService.resetPassword(jwtRequest)).isTrue();
//    }
//
//    @Test
//    void updateUserById() {
//        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(null));
//        when(userRepository.findById((long)1)).thenReturn(Optional.of(User.builder().build()));
//
//        assertThatThrownBy(() -> userService.updateUserById((long)2, User.builder().build(), null))
//                .isInstanceOf(ResourceNotFoundException.class);
//        when(userRepository.save(any())).thenReturn(User.builder().build());
//
//        User user = userService.updateUserById((long)1, User.builder().build(), null);
//
//        assertThat(user).isNotNull();
//        assertThat(user.equals(User.builder().build())).isTrue();
//    }
//
////    @Test
////    void findAll() {
////
////    }
////
////    @Test
////    void deleteUser() {
////    }
////
////    @Test
////    void findUserById() {
////    }
////
////    @Test
////    void getImage() {
////    }
//}