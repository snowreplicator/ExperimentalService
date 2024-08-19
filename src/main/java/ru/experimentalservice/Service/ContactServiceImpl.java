package ru.experimentalservice.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.experimentalservice.DTO.CreateContactDTO;
import ru.experimentalservice.Entity.Contact;
import ru.experimentalservice.Repository.ContactRepository;
import ru.experimentalservice.Service.Exception.DuplicateScreenNameException;
import ru.experimentalservice.Service.Interface.ContactService;
import ru.experimentalservice.ViewModel.ContactViewModel;
import ru.experimentalservice.ViewModel.ContactsViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ContactsViewModel getContacts() {
        List<Contact> contacts = contactRepository.findAll();

        List<ContactViewModel> contactViewModels = contacts
                .stream()
                .sorted(Comparator.comparing(Contact::getScreenName))
                .map(ContactViewModel::fromDomain)
                .collect(Collectors.toList());

        return new ContactsViewModel(contactViewModels);
    }

    @Transactional
    @Override
    public ContactViewModel createContact(CreateContactDTO createContactDTO) {
        Contact contact = new Contact();
        if (contactRepository.findByScreenName(createContactDTO.getScreenName()).isPresent())
            throw new DuplicateScreenNameException(createContactDTO.getScreenName());

        contact.setScreenName(createContactDTO.getScreenName());
        contact.setEmail(createContactDTO.getEmail());

        contact = contactRepository.save(contact);

        return ContactViewModel.fromDomain(contact);
    }

    @Transactional
    @Override
    public ContactViewModel createContactWithException(CreateContactDTO createContactDTO) {
        Contact contact = new Contact();
        if (contactRepository.findByScreenName(createContactDTO.getScreenName()).isPresent())
            throw new DuplicateScreenNameException(createContactDTO.getScreenName());

        contact.setScreenName(createContactDTO.getScreenName());
        contact.setEmail(createContactDTO.getEmail());

        contact = contactRepository.save(contact);

        throwArithmeticException();
        return ContactViewModel.fromDomain(contact);
    }

    public Contact createContact1(String screenName) {
        return new Contact(0L, screenName, "first contact" + currentTime());
    }

    public Contact createContact2(String screenName) {
        return new Contact(0L, screenName, "second contact" + currentTime());
    }

    private String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return now.format(formatter);
    }

    private void throwArithmeticException() {
        int x = 1;
        int y = 0;
        int z = x / y;
    }

}
