package com.don8.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResetPassword {
    private String email;
    private String password;
}
