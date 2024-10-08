package ru.experimentalservice.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.experimentalservice.DTO.CreateUserDTO;

import ru.experimentalservice.Service.Interface.UserService;
import ru.experimentalservice.ViewModel.UserViewModel;
import ru.experimentalservice.ViewModel.UsersViewModel;

@Tag(name = "UserController - контроллер для управления пользователями", description = "Операции связанные пользователями")
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получить пользователей", description = "Возвращает список всех пользователей")
    @GetMapping(path = "/getUsers")
    public ResponseEntity<UsersViewModel> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Добавить пользователя (user)", description = "В таблице user появляется новая запись")
    @PostMapping(path = "/createUser")
    public ResponseEntity<UserViewModel> createUser(@Valid @ModelAttribute CreateUserDTO createUserDTO) {
        return new ResponseEntity<>(userService.createUser(createUserDTO), HttpStatus.OK);
    }

    @Operation(summary = "Тест добавления 2х пользователей", description = "Попытка добавить 2х пользователей с исключением без транзакции")
    @PostMapping(path = "/createUserTest_v1")
    public ResponseEntity<UsersViewModel> createUserTest_v1() {
        return new ResponseEntity<>(userService.createUserTest_v1(), HttpStatus.OK);
    }

    @Operation(summary = "Тест добавления 2х пользователей", description = "Попытка добавить 2х пользователей с исключением в рамках одной транзации")
    @PostMapping(path = "/createUserTest_v2")
    public ResponseEntity<UsersViewModel> createUserTest_v2() {
        return new ResponseEntity<>(userService.createUserTest_v2(), HttpStatus.OK);
    }

    @Operation(summary = "Тест добавления 2х пользователей с контактами", description = "Попытка добавить 2х пользователей с исключением в рамках одной транзации")
    @PostMapping(path = "/createUserTest_v3")
    public ResponseEntity<UsersViewModel> createUserTest_v3() {
        return new ResponseEntity<>(userService.createUserTest_v3(), HttpStatus.OK);
    }

    @Operation(summary = "Тест добавления пользователя с контактом", description = "Попытка добавить пользователя с исключением в рамках одной транзации сталкиваем transactional методы")
    @PostMapping(path = "/createUserTest_v4")
    public ResponseEntity<UsersViewModel> createUserTest_v4() {
        return new ResponseEntity<>(userService.createUserTest_v4(), HttpStatus.OK);
    }

    @Operation(summary = "Тест добавления пользователя с контактом с исключением в контакте", description = "Попытка добавить пользователя с исключением в рамках одной транзации сталкиваем transactional методы и исключение выбрасываем во втором")
    @PostMapping(path = "/createUserTest_v5")
    public ResponseEntity<UsersViewModel> createUserTest_v5() {
        return new ResponseEntity<>(userService.createUserTest_v5(), HttpStatus.OK);
    }

}
