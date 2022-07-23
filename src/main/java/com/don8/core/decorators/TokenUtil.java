package com.don8.core.decorators;

import com.don8.config.JwtUtils;
import com.don8.core.model.dbentity.User;
import com.don8.core.model.exception.InvalidToken;
import com.don8.core.model.exception.ResourceNotFoundException;
import com.don8.core.port.inbound.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
@Component
public class TokenUtil {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    IUserService userService;

    public User getUserFromToken(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        String jwt = null;
        System.out.println("Auth Header: "+ headerAuth);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwt = headerAuth.substring(7, headerAuth.length());
        }
        if (!StringUtils.isEmpty(jwt) && jwtUtils.validateJwtToken(jwt)){
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            User user = userService.getUser(username);
            return user;
        }
        throw new InvalidToken("Invalid Token");
    }
}
