package ru.experimentalservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ru.experimentalservice.Service.Interface.DataCollectorService;

import java.util.List;
import java.util.Map;

@Service
public class DataCollectorServiceImpl implements DataCollectorService {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getEmails() {
        //String sql = "SELECT * FROM datacollectorservice.email";
        String sql = "SELECT * FROM email";
        var x = jdbcTemplate.queryForList(sql);
        return x;
    }

}
