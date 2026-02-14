package com.petconnect.petplatform.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Email(message = "Email invalid")
    @NotBlank(message = "Emailul este obligatoriu")
    private String email;

    @NotBlank(message = "Parola este obligatorie")
    private String password;
}
