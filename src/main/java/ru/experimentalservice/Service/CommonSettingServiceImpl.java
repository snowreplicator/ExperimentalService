package ru.experimentalservice.Service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import ru.experimentalservice.Entity.CommonSetting;
import ru.experimentalservice.Repository.CommonSettingRepository;
import ru.experimentalservice.Service.Common.CommonSettingConst;
import ru.experimentalservice.Service.Interface.CommonSettingService;
import ru.experimentalservice.ViewModel.SettingsViewModel;

@Service
@RequiredArgsConstructor
public class CommonSettingServiceImpl implements CommonSettingService {

    private final CommonSettingRepository commonSettingRepository;

    @Override
    public SettingsViewModel getSettings() {
        SettingsViewModel.LocaleSettingsViewModel locales = new SettingsViewModel.LocaleSettingsViewModel(
                getSetting(CommonSettingConst.defaultLocale, true),
                getSetting(CommonSettingConst.availableLocales, true));
        return new SettingsViewModel(locales);
    }

    @Override
    public String getSetting(String option, boolean useDefaultSetting) {
        CommonSetting commonSetting = commonSettingRepository.findById(option).orElse(null);
        if (useDefaultSetting && commonSetting == null) {
            commonSetting = CommonSetting.fillDefaultValue(option);
        }

        return commonSetting == null ? "" : commonSetting.getValue();
    }
}
