package ru.experimentalservice.Service.Interface;

import ru.experimentalservice.DTO.CreateContactDTO;
import ru.experimentalservice.Entity.Contact;
import ru.experimentalservice.ViewModel.ContactViewModel;
import ru.experimentalservice.ViewModel.ContactsViewModel;

public interface ContactService {

    ContactsViewModel getContacts();

    ContactViewModel createContact(CreateContactDTO createContactDTO);

    ContactViewModel createContactWithException(CreateContactDTO createContactDTO);

    Contact createContact1(String screenName);

    Contact createContact2(String screenName);

}
