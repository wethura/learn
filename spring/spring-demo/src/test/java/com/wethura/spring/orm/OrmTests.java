package com.wethura.spring.orm;

import com.wethura.spring.AbstractDemoTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * @author sola
 */
public class OrmTests extends AbstractDemoTests {

    @Autowired
    private JdbcTemplate template;


    @Test
    public void testJdbcForElasticsearch() {
//        template.execute("insert into account(name, password) values('wuweihua','password')");
        final SqlRowSet sqlRowSet = template.queryForRowSet("select count(*) from account");
    }
}
