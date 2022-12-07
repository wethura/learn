package com.wethura.shardingjdbc;

import com.wethura.shardingjdbc.algorithm.ShardingSnowFlakeKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AbstractNewsEntityTests extends Assertions {

    @Autowired
    protected JdbcTemplate template;

    @Autowired
    protected ShardingSnowFlakeKeyGenerator keyGenerator;

    /**
     * Delete all data before each test.
     */
    @BeforeEach
    public void beforeEach() {
//        template.execute("delete from " + NewsEntity.TABLE_NAME);
    }
}
