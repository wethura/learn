package com.wethura.shardingjdbc.algorithm;

import java.util.Collection;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

/**
 * @author sola
 **/
public class ShardingAlgorithm implements StandardShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        return availableTargetNames.stream().findFirst().get();
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
            RangeShardingValue<String> shardingValue) {
        return null;
    }
}
