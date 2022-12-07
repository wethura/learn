package com.wethura.shardingjdbc;

import com.wethura.shardingjdbc.entity.NewsEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * Tests for {@link NewsEntity}.
 */
public class NewsEntityTests extends AbstractNewsEntityTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsEntityTests.class);

    @Test
    @Disabled
    void testBatchInsertData_100lines() {
        // delete all datas.
        template.execute("delete from " + NewsEntity.TABLE_NAME);

        int batch = 2000, times = 500;
        for (int i = 0; i < times; i++) {
            StringBuilder builder = new StringBuilder("insert into " + NewsEntity.TABLE_NAME + " (id, title) values ");
            int start = i * batch;
            for (int j = start; j < start + batch; j++) {
                if (j == start + batch - 1) {
                    builder.append(String.format("(%d, 'wethura custom title %07d')", (Long) keyGenerator.generateKey(), j));
                } else {
                    builder.append(String.format("(%d, 'wethura custom title %07d'), ", (Long) keyGenerator.generateKey(), j));
                }
            }
            template.execute(builder.toString());
            LOGGER.info("batch sql execute finished, sqe no: {}", start / batch);
        }
    }

    @Test
    void testQueryPageable() {
        query("select * from " + NewsEntity.TABLE_NAME + " where title like 'wethura%' order by title limit 100000,10 ");
    }

    /**
     * Top N query optimization
     * when query the next page, carry the id to query next page, and condition not change.
     */
    @Test
    @Disabled
    void testQueryTopNOptimize() {
        // query("select * from " + NewsEntity.TABLE_NAME + " where title like 'wethura%' order by title limit 10 ");
        query("select * from " + NewsEntity.TABLE_NAME + " where id >= 798053817505488896 and title like 'wethura%' order by title limit 10 ");
        // query("select * from " + NewsEntity.TABLE_NAME + " where title like 'wethura%' order by title limit 110000,10 ");
        query("select * from " + NewsEntity.TABLE_NAME + " where id >= 798053842981691392 and title like 'wethura%' order by title limit 10 ");
        // query("select * from " + NewsEntity.TABLE_NAME + " where title like 'wethura%' order by title limit 120000,10 ");
        query("select * from " + NewsEntity.TABLE_NAME + " where id >= 798053844873322497 and title like 'wethura%' order by title limit 10 ");
    }

    private void query(String sql) {
        StopWatch watch = new StopWatch();
        watch.start();
        List<NewsEntity> list = template.query(sql,
                (rs, rowNum) -> new NewsEntity(rs.getLong("id"), rs.getString("title")));
        watch.stop();
        for (NewsEntity news : list) {
            LOGGER.info("{} -- {}", news.getId(), news.getTitle());
        }
        LOGGER.info("query times: {}", watch.getTotalTimeMillis());
    }
}
