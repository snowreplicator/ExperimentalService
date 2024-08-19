package ru.experimentalservice.Service.Interface;

import ru.experimentalservice.DTO.CreateContactDTO;
import ru.experimentalservice.ViewModel.ContactViewModel;
import ru.experimentalservice.ViewModel.ContactsViewModel;

public interface ContactService {

    ContactsViewModel getContacts();

    ContactViewModel createContact(CreateContactDTO createContactDTO);

}
