package ru.experimentalservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.experimentalservice.DTO.GetViewDataDTO;
import ru.experimentalservice.Service.Interface.DataCollectorService;
import ru.experimentalservice.ViewModel.ViewsStructViewModel;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Operation(summary = "Получить список схем", description = "Возвращает список всех схем из базы данных")
    @GetMapping(path = "/getSchemas")
    public ResponseEntity<List<Map<String, Object>>> getSchemas() {
        return new ResponseEntity<>(dataCollectorService.getSchemas(), HttpStatus.OK);
    }

    @Operation(summary = "Получить список таблиц из схемы datacollectorservice", description = "Возвращает список всех таблиц из указанной схемы")
    @GetMapping(path = "/getTables")
    public ResponseEntity<List<Map<String, Object>>> getTables() {
        return new ResponseEntity<>(dataCollectorService.getTables(), HttpStatus.OK);
    }

    @Operation(summary = "Получить список вьюх из схемы datacollectorservice", description = "Возвращает список всех вьюх из указанной схемы")
    @GetMapping(path = "/getViews")
    public ResponseEntity<List<Map<String, Object>>> getViews() {
        return new ResponseEntity<>(dataCollectorService.getViews(), HttpStatus.OK);
    }

    @Operation(summary = "Получить список полей из вьюхи datacollectorservice.user_emails", description = "Возвращает список всех полей из вьюхи и их тип данных")
    @GetMapping(path = "/getViewStruct")
    public ResponseEntity<List<Map<String, Object>>> getViewStruct() {
        return new ResponseEntity<>(dataCollectorService.getViewStruct(), HttpStatus.OK);
    }

    @Operation(summary = "Получить все данные из вьюхи datacollectorservice.user_emails", description = "Возвращает список всех записей из вьюхи")
    @GetMapping(path = "/getViewData")
    public ResponseEntity<List<Map<String, Object>>> getViewData() {
        return new ResponseEntity<>(dataCollectorService.getViewData(), HttpStatus.OK);
    }

    @Operation(summary = "Получить список вьюх и их поля", description = "Возвращает список всех вьюх и их аттрибутивный состав полей из указанной схемы")
    @GetMapping(path = "/getSchemaViews/{schema}")
    public ResponseEntity<ViewsStructViewModel> getSchemaViews(@PathVariable String schema) {
        schema = "datacollectorservice"; // !!!!!!! delete
        return new ResponseEntity<>(dataCollectorService.getSchemaViews(schema), HttpStatus.OK);
    }

    @Operation(summary = "Получить данные из указанных полей указанной таблицы", description = "Возвращает список данных для указанной таблицы/вьюхи для указанных полей")
    @PostMapping(path = "/getViewData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getViewData(@RequestBody GetViewDataDTO getViewDataDTO) {
        getViewDataDTO = new GetViewDataDTO("datacollectorservice", "user_emails", new ArrayList<>(Arrays.asList("user_id", "user_fio", "user_email"))); // !!! delete
        return new ResponseEntity<>(dataCollectorService.getViewData(getViewDataDTO), HttpStatus.OK);
    }
}
