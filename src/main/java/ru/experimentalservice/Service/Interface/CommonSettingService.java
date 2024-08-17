package ru.experimentalservice.Service.Interface;

import ru.experimentalservice.ViewModel.SettingsViewModel;

public interface CommonSettingService {

   SettingsViewModel getSettings();

   String getSetting(String option, boolean useDefaultSetting);
}
