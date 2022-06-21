package com.don8.service;

import com.don8.model.exception.ResourceNotFoundException;
import com.don8.model.request.JwtRequest;
import com.don8.model.dbentity.User;
import com.don8.port.inbound.IUserService;
import com.don8.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User storeUser(User user) throws JsonProcessingException {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new ValidationException("Email id is already in use please reset password if you have forgotten");
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("Inserting User: " + objectMapper.writeValueAsString(user));
        userRepository.save(user);
        return user;
    }

    @Override
    public Boolean resetPassword(JwtRequest jwtRequest) {
        User user = userRepository.findByEmail(jwtRequest.getEmail()).orElse(null);
        if(user==null)
            return false;
        user.setPassword(encoder.encode(jwtRequest.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User updateUserById(Long userId, User u) {
        return userRepository.findById(userId).map(user -> {
            user.setName(u.getName());
            user.setPhone(u.getPhone());
            user.setProfile_image(u.getProfile_image());
            user.setRole(u.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }
}