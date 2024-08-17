package ru.experimentalservice.Service.Interface;

import ru.experimentalservice.DTO.CreateUserDTO;
import ru.experimentalservice.ViewModel.UserViewModel;
import ru.experimentalservice.ViewModel.UsersViewModel;

public interface UserService {

    UsersViewModel getUsers();

    UserViewModel createUser(CreateUserDTO createPrintFormDTO);

    UsersViewModel createUserTest_v1();

    UsersViewModel createUserTest_v2();

}
