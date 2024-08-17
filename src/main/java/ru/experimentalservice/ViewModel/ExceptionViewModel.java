package ru.experimentalservice.ViewModel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class ExceptionViewModel {
    private String code;
    private String message;
}
