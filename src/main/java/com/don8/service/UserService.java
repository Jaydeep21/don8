package com.don8.service;

import com.don8.model.JwtRequest;
import com.don8.model.ResetPassword;
import com.don8.model.User;
import com.don8.port.inbound.IUserService;
import com.don8.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.sql.Timestamp;

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
        user.setCreated_date(new Timestamp(System.currentTimeMillis()));
        user.setUpdated_date(new Timestamp(System.currentTimeMillis()));
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
        user.setUpdated_date(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return true;
    }
}