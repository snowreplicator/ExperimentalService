package ru.experimentalservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ru.experimentalservice.Service.Common.CommonSettingConst;

@Entity
@Table(name = "commonsetting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonSetting {

    @Id
    @Column(name = "option", nullable = false)
    private String option;

    @Column(name = "value", nullable = true)
    private String value;

    public static CommonSetting fillDefaultValue(String option)
    {
        switch (option)
        {
            case CommonSettingConst.defaultLocale:
                return new CommonSetting(option, CommonSettingConst.defaultLocaleDefaultValue);

            case CommonSettingConst.availableLocales:
                return new CommonSetting(option, CommonSettingConst.availableLocalesDefaultValue);

            default:
                return null;
        }
    }
}
