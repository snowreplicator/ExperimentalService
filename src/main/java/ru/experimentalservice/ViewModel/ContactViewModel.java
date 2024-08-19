package ru.experimentalservice.ViewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.experimentalservice.Entity.Contact;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactViewModel {
    private long id;
    private String screenName;
    private String email;

    public static ContactViewModel fromDomain(Contact contact) {
        return new ContactViewModel(
                contact.getId(),
                contact.getScreenName(),
                contact.getEmail()
        );
    }
}
