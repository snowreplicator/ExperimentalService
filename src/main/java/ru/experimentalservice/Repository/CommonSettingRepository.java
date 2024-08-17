package ru.experimentalservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.experimentalservice.Entity.CommonSetting;

public interface CommonSettingRepository extends JpaRepository<CommonSetting, String> {

}
