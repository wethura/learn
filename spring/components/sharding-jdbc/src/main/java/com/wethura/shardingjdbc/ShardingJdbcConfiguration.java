package com.wethura.shardingjdbc;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.single.api.config.SingleRuleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sola
 **/
@Configuration
public class ShardingJdbcConfiguration {

    @Bean
    DataSource dataSource(@Autowired DataSourceProperties properties) throws SQLException {

        if (properties == null) {
            System.exit(0);
        }

        final Map<String, DataSource> datasources = createDataSourceMap();

        final ArrayList<RuleConfiguration> rules = new ArrayList<>();
        final ShardingRuleConfiguration rule = new ShardingRuleConfiguration();

        final Properties props = new Properties();
        props.put("strategy", "standard");
        props.put("algorithmClassName", "com.wethura.shardingjdbc.algorithm.ShardingAlgorithm");
        rule.getShardingAlgorithms().put("test-sharding", new AlgorithmConfiguration("CLASS_BASED", props));

        final ShardingTableRuleConfiguration table = new ShardingTableRuleConfiguration("news", "ds0.news");
        table.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("id", "test-sharding"));
        rule.getTables().add(table);
        rule.setDefaultDatabaseShardingStrategy(new NoneShardingStrategyConfiguration());
        rule.setDefaultTableShardingStrategy(new NoneShardingStrategyConfiguration());

        final SingleRuleConfiguration singleRule = new SingleRuleConfiguration(Arrays.asList("*.*"), "default");

        rules.add(singleRule);
        rules.add(rule);

        return ShardingSphereDataSourceFactory.createDataSource("default", datasources, rules, new Properties());
    }

    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        HikariDataSource dataSource0 = new HikariDataSource();
        dataSource0.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource0.setJdbcUrl(
                "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&verifyServerCertificate=false");
        dataSource0.setUsername("root");
        dataSource0.setPassword("123456");
        dataSourceMap.put("ds0", dataSource0);
        dataSourceMap.put("default", dataSource0);

        // 配置第二个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl(
                "jdbc:mysql://127.0.0.1:3306/bingo-ufs-sub-01?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&verifyServerCertificate=false");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds10", dataSource1);

        return dataSourceMap;
    }
}
