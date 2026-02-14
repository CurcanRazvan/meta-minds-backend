package com.petconnect.petplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequestDto {

    @NotBlank(message = "Numele este obligatoriu")
    private String firstName;

    @NotBlank(message = "Prenumele este obligatoriu")
    private String lastName;

    @NotBlank(message = "Numărul de telefon este obligatoriu")
    @Pattern(regexp = "\\d{10}", message = "Număr de telefon invalid")
    private String phoneNumber;
}
