package ru.experimentalservice.Service.Interface;

import ru.experimentalservice.DTO.GetViewDataDTO;
import ru.experimentalservice.ViewModel.ViewsStructViewModel;

import java.util.List;
import java.util.Map;

public interface DataCollectorService {

    public List<Map<String, Object>> getEmails();

    public List<Map<String, Object>> getSchemas();

    public List<Map<String, Object>> getTables();

    public List<Map<String, Object>> getViews();

    public List<Map<String, Object>> getViewStruct();

    public List<Map<String, Object>> getViewData();

    public ViewsStructViewModel getSchemaViews(String schema);

    public Map<String, Object> getViewData(GetViewDataDTO getViewDataDTO);

}
