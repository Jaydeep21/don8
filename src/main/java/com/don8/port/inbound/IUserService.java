package com.don8.port.inbound;

import com.don8.model.dbentity.Address;
import com.don8.model.request.JwtRequest;
import com.don8.model.dbentity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    public User getUser(String email);
    public User storeUser(User user) throws JsonProcessingException;
    public Boolean resetPassword(JwtRequest jwtRequest);
    public User updateUserById(Long userId, User user);
    public Page<User> findAll(Pageable page);
    public void deleteUser(Long userId);
    public User findUserById(Long userId);
}
