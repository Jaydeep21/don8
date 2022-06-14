package com.don8.controller;

import com.don8.config.JwtUtils;
import com.don8.model.*;
//import com.don8.port.outbound.IJwtUserDetailsService;
import com.don8.port.inbound.IUserService;
import com.don8.port.outbound.IEmailVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    @Autowired
    IEmailVerification emailVerification;

    @Autowired
    IUserService userService;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        return ResponseEntity.ok(GenericResponse.builder().body(jwt).message("Success").build());

    }

    @PostMapping("/otp")
    public ResponseEntity<GenericResponse> sendEmail( @RequestBody Email email){
        String otp;
        if(userService.getUser(email.getEmail())!=null)
            return new ResponseEntity<GenericResponse>(GenericResponse.builder().body("User Already Exists with this Email").message("Error").build(), HttpStatus.BAD_REQUEST);
        try {
            otp = emailVerification.sendEmail(email.getEmail());
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(GenericResponse.builder().body(null).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(GenericResponse.builder().body(otp).message("Success").build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid  @NotNull @RequestBody User user) {
        try {
            user = userService.storeUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(GenericResponse.builder().body(e.getMessage()).message("Error").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(GenericResponse.builder().body(user).message("Success").build());
    }
    @PostMapping("/forgot")
    public ResponseEntity<GenericResponse> resetPassword( @RequestBody Email email){
        String otp;
        try {
            otp = emailVerification.sendEmail(email.getEmail());
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<GenericResponse>(GenericResponse.builder().body(null).message(e.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(GenericResponse.builder().body(otp).message("Success").build());
    }
    @PostMapping("/password")
    public ResponseEntity<GenericResponse> changePassword( @RequestBody JwtRequest jwtRequest){
        if(userService.resetPassword(jwtRequest))
            return ResponseEntity.ok(GenericResponse.builder().body("Updated Password Successfully").message("Success").build());
        return ResponseEntity.ok(GenericResponse.builder().body("Could not update password as email does not exists").message("Error").build());

    }

}
