package ru.experimentalservice.ViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.experimentalservice.Entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserViewModel {

    private long id;
    private String screenName;
    private String fio;

    public static UserViewModel fromDomain(User user) {
        return new UserViewModel(
                user.getId(),
                user.getScreenName(),
                user.getFio()
        );
    }

}
