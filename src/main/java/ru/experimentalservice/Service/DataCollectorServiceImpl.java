package ru.experimentalservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import ru.experimentalservice.DTO.GetViewDataDTO;
import ru.experimentalservice.Service.Interface.DataCollectorService;
import ru.experimentalservice.ViewModel.TableStruct;
import ru.experimentalservice.ViewModel.ViewStructViewModel;
import ru.experimentalservice.ViewModel.ViewsStructViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataCollectorServiceImpl implements DataCollectorService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataCollectorServiceImpl(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getEmails() {
        //String sql = "SELECT * FROM datacollectorservice.email";
        String sql = "SELECT * FROM email";
        var x = jdbcTemplate.queryForList(sql);
        return x;
    }

    @Override
    public List<Map<String, Object>> getSchemas() {
        String sql = "SELECT schema_name FROM information_schema.schemata";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getTables() {
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'datacollectorservice';";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getViews() {
        String sql = "SELECT table_name FROM information_schema.views WHERE table_schema = 'datacollectorservice'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getViewStruct() {
        String sql = "SELECT column_name, data_type FROM information_schema.columns WHERE table_schema = 'datacollectorservice' AND table_name = 'user_emails'";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getViewData() {
        String sql = "SELECT user_id, user_fio, user_email FROM datacollectorservice.user_emails";
        return jdbcTemplate.queryForList(sql);
    }

    // получаем список вьюх указанной схемы и их аттрибутивный состав полей
    @Override
    public ViewsStructViewModel getSchemaViews(String schema) {
        List<String> views = getViews(schema);
        return getViewsStruct(schema, views);
    }

    private List<String> getViews(String schema) {
        String sql = "SELECT table_name FROM information_schema.views WHERE table_schema = ?";
        return jdbcTemplate.query(sql, new Object[]{schema}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("table_name");
            }
        });
    }

    private ViewsStructViewModel getViewsStruct(String schema, List<String> views) {
        List<ViewStructViewModel> viewsModels = new ArrayList<ViewStructViewModel>();
        for (String view : views) {
            List<TableStruct> struct = getViewStruct(schema, view);
            ViewStructViewModel viewStructViewModel = new ViewStructViewModel(view, struct);
            viewsModels.add(viewStructViewModel);
        }
        return new ViewsStructViewModel(viewsModels);
    }

    private List<TableStruct> getViewStruct(String schema, String view) {
        String sql = "SELECT column_name, data_type FROM information_schema.columns WHERE table_schema = ? AND table_name = ?";
        return jdbcTemplate.query(sql, new Object[]{schema, view}, new RowMapper<TableStruct>() {
            @Override
            public TableStruct mapRow(ResultSet rs, int rowNum) throws SQLException {
                TableStruct tableStruct = new TableStruct();
                tableStruct.setColumnName(rs.getString("column_name"));
                tableStruct.setDataType(rs.getString("data_type"));
                return tableStruct;
            }
        });
    }

    // получаем данные из указанных полей указанной вьюхи
    @Override
    public Map<String, Object> getViewData(GetViewDataDTO getViewDataDTO) {
        return null;
    }

}
