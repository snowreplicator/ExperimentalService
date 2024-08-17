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
@RequestMapping("/api/setting")
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

}
