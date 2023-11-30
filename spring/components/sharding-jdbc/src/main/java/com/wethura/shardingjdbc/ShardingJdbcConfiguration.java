package com.wethura.shardingjdbc;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sola
 **/
@Configuration
public class ShardingJdbcConfiguration {

    @Bean
    DataSource dataSource() throws SQLException {

        final Map<String, DataSource> datasources = createDataSourceMap();

//        return datasources.get("ds0");

        final ArrayList<RuleConfiguration> rules = new ArrayList<>();
//        final ShardingRuleConfiguration rule = new ShardingRuleConfiguration();
//
//        final Properties props = new Properties();
//        props.put("strategy", "standard");
//        props.put("algorithmClassName", "com.wethura.shardingjdbc.algorithm.ShardingAlgorithm");
//        rule.getShardingAlgorithms().put("test-sharding", new AlgorithmConfiguration("CLASS_BASED", props));

//        final ShardingTableRuleConfiguration table = new ShardingTableRuleConfiguration("news", "ds0.news");
//        table.setTableShardingStrategy(new StandardShardingStrategyConfiguration("id", "test-shading"));
//        rule.getTables().add(table);

        return ShardingSphereDataSourceFactory.createDataSource("test", datasources, rules, new Properties());
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl(
                "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&verifyServerCertificate=false");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第二个数据源
//        HikariDataSource dataSource2 = new HikariDataSource();
//        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource2.setJdbcUrl("jdbc:mysql://wethura:3306/ds1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&verifyServerCertificate=false");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("wethura");
//        dataSourceMap.put("ds1", dataSource2);

        return dataSourceMap;
    }
}
