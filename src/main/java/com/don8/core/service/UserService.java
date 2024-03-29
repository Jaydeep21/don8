package com.don8.core.service;

import com.don8.core.model.exception.ResourceNotFoundException;
import com.don8.core.model.request.JwtRequest;
import com.don8.core.model.dbentity.User;
import com.don8.core.port.inbound.IUserService;
import com.don8.core.port.outbound.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${prod.url}")
    String url;
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
    public User updateUserById(Long userId, User u, MultipartFile image) {
        return userRepository.findById(userId).map(user -> {
            if(image!= null){
                user.setImage_type(image.getContentType());
                user.setImage_url(url+"user/profile/" + String.valueOf(userId));
                try {
                    user.setProfile_image(image.getBytes());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            user.setName(u.getName());
            user.setPhone(u.getPhone());
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

    @Override
    public byte[] getImage(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
        if(user.getProfile_image()==null)
           throw new ResourceNotFoundException("UserId " + userId + " does not have a profile image");
        return user.getProfile_image();
    }

}