package com.wethura.shardingjdbc.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author sola
 **/
public class ShardingPreciseTableAlgorithm implements PreciseShardingAlgorithm<Long> {

    private static final IllegalStateException TABLE_NOTFOUND_EXCEPTION = new IllegalStateException("table not exists.");

    @Override
    public String doSharding(Collection<String> tables, PreciseShardingValue<Long> shardingValue) {
        for (String table : tables) {
            return table;
        }
        throw TABLE_NOTFOUND_EXCEPTION;
    }
}
