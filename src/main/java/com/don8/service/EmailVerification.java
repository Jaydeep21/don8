package com.don8.service;

import com.don8.port.outbound.IEmailVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class EmailVerification implements IEmailVerification {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public String sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@don8.com");
        message.setTo(email);
        message.setSubject("OTP Verification for DON8");
        String number = String.format("%04d", ThreadLocalRandom.current().nextInt(1000, 9999 + 1));
        message.setText("Thank you for choosing DON8 please enter this OTP: " + number);
        emailSender.send(message);
        return number;
    }
}
