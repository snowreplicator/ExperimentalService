package ru.experimentalservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.experimentalservice.Service.Interface.CommonSettingService;
import ru.experimentalservice.ViewModel.SettingsViewModel;

@Tag(name = "SettingController - контроллер c настройками сервиса", description = "Общие операции связанные с различными настройками сервиса")
@RestController
@RequestMapping("/api/setting")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SettingController {

    private final CommonSettingService commonSettingService;

    @Operation(summary = "Получить настройки", description = "Возвращает все найстройки сервиса")
    @GetMapping(path = "/getSettings")
    public ResponseEntity<SettingsViewModel> getSettings() {
        return new ResponseEntity<>(commonSettingService.getSettings(), HttpStatus.OK);
    }
}
