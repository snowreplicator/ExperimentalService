package ru.experimentalservice.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.experimentalservice.DTO.CreateContactDTO;
import ru.experimentalservice.DTO.CreateUserDTO;
import ru.experimentalservice.Entity.Contact;
import ru.experimentalservice.Entity.User;
import ru.experimentalservice.Repository.ContactRepository;
import ru.experimentalservice.Repository.UserRepository;
import ru.experimentalservice.Service.Exception.DuplicateScreenNameException;
import ru.experimentalservice.Service.Interface.ContactService;
import ru.experimentalservice.Service.Interface.UserService;
import ru.experimentalservice.ViewModel.UserViewModel;
import ru.experimentalservice.ViewModel.UsersViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ContactService contactService;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Override
    public UsersViewModel getUsers() {
        List<User> users = userRepository.findAll();

        List<UserViewModel> userViewModels = users
                .stream()
                .sorted(Comparator.comparing(User::getScreenName))
                .map(UserViewModel::fromDomain)
                .collect(Collectors.toList());

        return new UsersViewModel(userViewModels);
    }

    @Transactional
    @Override
    public UserViewModel createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        if (userRepository.findByScreenName(createUserDTO.getScreenName()).isPresent())
            throw new DuplicateScreenNameException(createUserDTO.getScreenName());

        user.setScreenName(createUserDTO.getScreenName());
        user.setFio(createUserDTO.getFio());

        user = userRepository.save(user);

        return UserViewModel.fromDomain(user);
    }


    // первый пользователь добавиться в базу, второй нет
    // причина они пошли в разных транзакциях
    @Override
    public UsersViewModel createUserTest_v1() {
        User user1 = createUser1();
        User user2 = createUser2();

        userRepository.save(user1);
        throwArithmeticException();
        userRepository.save(user2);

        return getUsers();
    }

    // никто не добавиться в базу, все происходит в рамках одной транзации - @Transactional
    // выкинется исключение и произойдет откат
    @Transactional
    @Override
    public UsersViewModel createUserTest_v2() {
        User user1 = createUser1();
        User user2 = createUser2();

        userRepository.save(user1);
        throwArithmeticException();
        userRepository.save(user2);

        return getUsers();
    }

    // в базу никто не добавиться ни в одну из таблиц - @Transactional
    // выкинется исключение и произойдет откат
    @Transactional
    @Override
    public UsersViewModel createUserTest_v3() {
        User user1 = createUser1();
        User user2 = createUser2();

        Contact contact1 = contactService.createContact1(user1.getScreenName());
        Contact contact2 = contactService.createContact2(user2.getScreenName());

        userRepository.save(user1);
        contactRepository.save(contact1);
        throwArithmeticException();
        userRepository.save(user2);
        contactRepository.save(contact2);

        return getUsers();
    }

    // в базу в обе таблицы ничего не добавиться - @Transactional
    // причем contactService.createContact - так же помечен аннотацией  @Transactional
    // видимо они успешно поглощают друг друга и не нужно париться чтобы она была одна на главной функции
    @Transactional
    @Override
    public UsersViewModel createUserTest_v4() {
        User user = new User(0L, "user_with_contact_" + currentTime(), "user with contact fio");
        userRepository.save(user);

        throwArithmeticException();

        CreateContactDTO createContactDTO = new CreateContactDTO();
        createContactDTO.setScreenName(user.getScreenName());
        createContactDTO.setEmail("user with contact " + currentTime());
        contactService.createContact(createContactDTO);

        return getUsers();
    }


    // в базу в обе таблицы ничего не добавиться - @Transactional
    // причем contactService.createContact - так же помечен аннотацией @Transactional
    // и тут исключение уже внутри contactService.createContact - и все равно он нормально понимает транзацию
    // видимо почти не о чем не надо париться, все уже продумано за нас
    @Transactional
    @Override
    public UsersViewModel createUserTest_v5() {
        User user = new User(0L, "user_with_contact_exception_in_contact" + currentTime(), "user with contact exception fio");
        userRepository.save(user);

        CreateContactDTO createContactDTO = new CreateContactDTO();
        createContactDTO.setScreenName(user.getScreenName());
        createContactDTO.setEmail("user with contact exception " + currentTime());
        contactService.createContactWithException(createContactDTO);

        return getUsers();
    }


    private User createUser1() {
        return new User(0L, "user_1 - " + currentTime(), "first user");
    }

    private User createUser2() {
        return new User(0L, "user_2 - " + currentTime(), "second user");
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
