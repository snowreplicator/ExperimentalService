package ru.experimentalservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "SystemController - системный контроллер", description = "Общие операции связанные с сервисом")
@RestController
@RequestMapping("/api/system")
@CrossOrigin(origins = "*")
public class SystemController {

    private final BuildProperties buildProperties;
    private final Environment environment;

    @Autowired
    public SystemController(BuildProperties buildProperties, Environment environment) {
        this.buildProperties = buildProperties;
        this.environment = environment;
    }

    @Operation(summary = "Получить информацию о сервисе", description = "Возвращает название артефакта, версию и профиль запуска")
    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        var artifact = buildProperties.getArtifact();
        var version = buildProperties.getVersion();
        var profile = environment.getActiveProfiles().length > 0 ? environment.getActiveProfiles()[0] : null;

        Map<String, Object> info = new HashMap<>();
        info.put("version", version != null ? version : "Unknown");
        info.put("artifact", artifact != null ? artifact : "Unknown");
        info.put("profile", profile != null ? profile : "Unknown");

        return info;
    }

}
