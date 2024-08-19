package ru.experimentalservice.DTO;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateContactDTO {

    @NotBlank(message = "{ScreenName is required}")
    private String screenName;

    private String email;

}
