//package com.don8.controller;
//
//import com.don8.config.TestSecurityConfig;
//import com.don8.model.dbentity.User;
//import com.don8.port.inbound.IUserService;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
////import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.function.Function;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////import org.junit.platform.runner.JUnitPlatform;
//@SpringBootTest()
////classes = TestSecurityConfig.class
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
////@TestPropertySource(locations="classpath:test.properties")
//class UserControllerTest {
//    @InjectMocks
//    UserController userController;
//
//    @Mock
//    IUserService userService;
//    @Autowired
//    private MockMvc mockMvc;
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void getAllUser() {
//        List<User> users = new ArrayList<>();
//        users.add(User.builder().build());
//        users.add(User.builder().build());
//        users.add(User.builder().build());
//        Page<User> page =  new Page<User>() {
//            @Override
//            public int getTotalPages() {
//                return 1;
//            }
//
//            @Override
//            public long getTotalElements() {
//                return 3;
//            }
//
//            @Override
//            public <U> Page<U> map(Function<? super User, ? extends U> function) {
//                return null;
//            }
//
//            @Override
//            public int getNumber() {
//                return 0;
//            }
//
//            @Override
//            public int getSize() {
//                return 3;
//            }
//
//            @Override
//            public int getNumberOfElements() {
//                return 3;
//            }
//
//            @Override
//            public List<User> getContent() {
//                return users;
//            }
//
//            @Override
//            public boolean hasContent() {
//                return true;
//            }
//
//            @Override
//            public Sort getSort() {
//                return null;
//            }
//
//            @Override
//            public boolean isFirst() {
//                return false;
//            }
//
//            @Override
//            public boolean isLast() {
//                return false;
//            }
//
//            @Override
//            public boolean hasNext() {
//                return false;
//            }
//
//            @Override
//            public boolean hasPrevious() {
//                return false;
//            }
//
//            @Override
//            public Pageable nextPageable() {
//                return null;
//            }
//
//            @Override
//            public Pageable previousPageable() {
//                return null;
//            }
//
//            @Override
//            public Iterator<User> iterator() {
//                return null;
//            }
//        };
//
//        when(userService.findAll(any())).thenReturn(page);
//
//        Page<User> user = userController.getAllUser(null);
//
//        assertThat(user.getContent().size()).isEqualTo(3);
//        assertThat(user.getContent().get(0)).isNotNull();
//    }
//
//    @Test
//    void getUser() {
//        when(userService.findUserById((long)1)).thenReturn(User.builder()
//                .uid((long)1)
//                .email("asdf@gmail.com")
//                .phone(new BigInteger("1234567890"))
//                .build());
//
//        User user = userController.getUser((long)1);
//
//        assertThat(user.getPhone()).isEqualTo(new BigInteger("1234567890"));
//        assertThat(user.getEmail().equals("asdf@gmail.com")).isTrue();
//    }
//
//    @Test
//    void updateUser() throws Exception {
////        when(userService.updateUserById((long)1, User.builder().build(), null))
////                .thenReturn(User.builder()
////                        .name("Testing")
////                        .email("asdf@gmail.com")
////                        .build());
//        this.mockMvc.perform(put("/api/user/{userId}", 1).contentType( { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE }))
//                .andDo(print()).andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void deleteUser() {
//    }
//
//    @Test
//    void getProfile() {
//    }
//}