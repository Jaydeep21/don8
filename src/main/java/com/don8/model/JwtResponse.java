package com.don8.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse implements Serializable {

    private String token;
    private BigInteger uid;
    private String username;
    private String email;
    private Date startDate;
    private Date expiryDate;
}
