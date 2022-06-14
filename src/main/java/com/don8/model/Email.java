package com.don8.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Email {
    @NotBlank(message = "Email is mandatory")
    String email;
}
