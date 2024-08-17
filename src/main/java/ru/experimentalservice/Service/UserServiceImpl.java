package ru.experimentalservice.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.experimentalservice.DTO.CreateUserDTO;
import ru.experimentalservice.Entity.User;
import ru.experimentalservice.Repository.UserRepository;
import ru.experimentalservice.Service.Exception.DuplicateScreenNameException;
import ru.experimentalservice.Service.Interface.UserService;
import ru.experimentalservice.ViewModel.UserViewModel;
import ru.experimentalservice.ViewModel.UsersViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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

    // никто не добавиться в базу, все происходит в рамках одной транзации -  @Transactional
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

    private User createUser1() {
        return new User(0L,"user_1_" + UUID.randomUUID(),"first user");
    }

    private User createUser2() {
        return new User(0L,"user_2_" + UUID.randomUUID(),"second user");
    }

    private void throwArithmeticException() {
        int x = 1;
        int y = 0;
        int z = x/y;
    }

}
