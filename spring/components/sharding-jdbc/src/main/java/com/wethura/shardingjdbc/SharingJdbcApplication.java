package com.wethura.shardingjdbc;

import com.wethura.shardingjdbc.algorithm.ShardingPreciseDatabaseAlgorithm;
import com.wethura.shardingjdbc.algorithm.ShardingSnowFlakeKeyGenerator;
import com.wethura.shardingjdbc.algorithm.SharingRangeDatabaseAlgorithm;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SharingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharingJdbcApplication.class, args);
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        return new KeyGeneratorConfiguration(ShardingSnowFlakeKeyGenerator.ALGORITHM_NAME, "id");
    }

    @Bean
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new NoneShardingStrategyConfiguration());
        shardingRuleConfig.setDefaultDataSourceName("ds0");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("none");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        result.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",
                new ShardingPreciseDatabaseAlgorithm(), new SharingRangeDatabaseAlgorithm()));
        return result;
    }


    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setJdbcUrl(
                "jdbc:mysql://10.8.18.162:30101/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false&verifyServerCertificate=false");
        dataSource1.setUsername("root");
        dataSource1.setPassword("pass@word");
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
