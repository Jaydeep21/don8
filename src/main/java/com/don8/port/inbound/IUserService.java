package com.don8.port.inbound;

import com.don8.model.JwtRequest;
import com.don8.model.ResetPassword;
import com.don8.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IUserService {

    public User getUser(String email);
    public User storeUser(User user) throws JsonProcessingException;
    public Boolean resetPassword(JwtRequest jwtRequest);
}
