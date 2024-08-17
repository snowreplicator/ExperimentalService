package ru.experimentalservice.Config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "allowed-mime-types")
@Data
public class MimeTypesConfig {
    private List<String> mimeTypes;
}