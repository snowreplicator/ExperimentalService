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

import ru.experimentalservice.Service.Interface.DataCollectorService;

import java.util.List;
import java.util.Map;

@Tag(name = "JdbcTestController - для тестирования jdbc вызовов", description = "Операции связанные с выборками jdbc")
@RestController
@RequestMapping("/api/jdbc-test")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JdbcTestController {

    private final DataCollectorService dataCollectorService;

    @Operation(summary = "Получить данные из email", description = "Возвращает список всех записей из таблицы email по jdbc")
    @GetMapping(path = "/getEmails")
    public ResponseEntity<List<Map<String, Object>>> getEmails() {
        return new ResponseEntity<>(dataCollectorService.getEmails(), HttpStatus.OK);
    }
}
