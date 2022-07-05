package com.don8.port.inbound;

import com.don8.model.dbentity.Address;
import com.don8.model.request.JwtRequest;
import com.don8.model.dbentity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {

    User getUser(String email);
    User storeUser(User user) throws JsonProcessingException;
    Boolean resetPassword(JwtRequest jwtRequest);
    User updateUserById(Long userId, User user, MultipartFile image);
    Page<User> findAll(Pageable page);
    void deleteUser(Long userId);
    User findUserById(Long userId);
    byte[] getImage(Long userId);
}
