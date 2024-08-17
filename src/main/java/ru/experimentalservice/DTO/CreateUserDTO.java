package ru.experimentalservice.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotBlank(message = "{ScreenName is required}")
    private String screenName;

    private String fio;

}
