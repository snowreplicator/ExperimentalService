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

import ru.experimentalservice.DTO.CreateContactDTO;
import ru.experimentalservice.Service.Interface.ContactService;
import ru.experimentalservice.ViewModel.ContactViewModel;
import ru.experimentalservice.ViewModel.ContactsViewModel;

@Tag(name = "ContactController - расширенная контактная информация о пользователе", description = "Общие связанные с контактной инофрмацией о пользователях")
@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @Operation(summary = "Получить контакты", description = "Возвращает список всех контактов")
    @GetMapping(path = "/getContacts")
    public ResponseEntity<ContactsViewModel> getContacts() {
        return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    }

    @Operation(summary = "Добавить контакт (contact)", description = "В таблице contact появляется новая запись")
    @PostMapping(path = "/createContact")
    public ResponseEntity<ContactViewModel> createContact(@Valid @ModelAttribute CreateContactDTO createContactDTO) {
        return new ResponseEntity<>(contactService.createContact(createContactDTO), HttpStatus.OK);
    }

}
