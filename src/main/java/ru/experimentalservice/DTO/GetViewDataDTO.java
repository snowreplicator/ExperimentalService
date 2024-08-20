package ru.experimentalservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetViewDataDTO {
    private String schema;
    private String view;
    private List<String> fields;
}
