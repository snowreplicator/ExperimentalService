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

}
