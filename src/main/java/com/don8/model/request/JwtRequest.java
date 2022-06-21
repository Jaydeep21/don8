package com.don8.model.request;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String email;
    private String password;
}
