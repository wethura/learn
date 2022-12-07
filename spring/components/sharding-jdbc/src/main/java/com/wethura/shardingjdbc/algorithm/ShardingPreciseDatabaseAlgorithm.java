package com.wethura.shardingjdbc.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author sola
 **/
public class ShardingPreciseDatabaseAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> databases, PreciseShardingValue<Long> shardingValue) {
        int index = (int) (shardingValue.getValue() % 2);
        for (String database : databases) {
            if (index-- == 0) {
//                System.out.println(String.format("select database %s", database));
                return database;
            }
        }
        throw new IllegalStateException();
    }
}
