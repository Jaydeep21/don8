package com.don8.controller;

import com.don8.model.dbentity.User;
import com.don8.port.inbound.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ObjectMapper mapper;
    @GetMapping("")
    public Page<User> getAllUser(Pageable pageable) {
        return userService.findAll(pageable);
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PutMapping(value = "/{userId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public User updateUser(@PathVariable Long userId,
                           @Valid @RequestParam("user") String user,
                           @RequestParam(value = "image", required = false) MultipartFile file) {

        User modelDTO = null;
        try {
            modelDTO = mapper.readValue(user, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userService.updateUserById(userId, modelDTO, file);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/profile/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getProfile(@PathVariable Long userId){
        return new ByteArrayResource(userService.getImage(userId));
    }

}
