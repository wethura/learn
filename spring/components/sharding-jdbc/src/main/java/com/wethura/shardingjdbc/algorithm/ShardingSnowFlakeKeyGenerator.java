package com.wethura.shardingjdbc.algorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.shardingsphere.infra.instance.InstanceContext;
import org.apache.shardingsphere.infra.instance.InstanceContextAware;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;
import org.springframework.stereotype.Component;

/**
 * custom snowflake id generator.
 *
 * @author sola
 **/
@Component
public class ShardingSnowFlakeKeyGenerator implements KeyGenerateAlgorithm, InstanceContextAware {

    public static final String ALGORITHM_NAME = "UFS_SHARDING_SNOWFLAKE";

    private static final long START_TIMESTAMP = 1480166465631L;
    private static final long MAX_SEQUENCE = 4095L;
    private static final long MACHINE_LEFT = 12L;
    private static final long DATACENTER_LEFT = 17L;
    private static final long TIMESTAMP_LEFT = 22L;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;
    private final long offsetTimestamp;
    private final long datacenterId;
    private final long machineId;
    private Properties properties = new Properties();

    public ShardingSnowFlakeKeyGenerator() {
        this(getDataCenterId(), getWorkId());
    }

    public ShardingSnowFlakeKeyGenerator(long datacenterId, long machineId) {
        if (datacenterId <= 31L && datacenterId >= 0L) {
            if (machineId <= 31L && machineId >= 0L) {
                this.datacenterId = datacenterId;
                this.machineId = machineId;
            } else {
                throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
            }
        } else {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }

        offsetTimestamp = 3600000;
    }

    @SuppressWarnings("java:S1166")
    private static Long getWorkId() {
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for (int b : ints) {
                sums += b;
            }
            return (long) (sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0, 31);
        }
    }

    private static Long getDataCenterId() {
        String hostName = SystemUtils.getHostName();
        if (null != hostName) {
            int[] ints = StringUtils.toCodePoints(hostName);
            int sums = 0;
            for (int i : ints) {
                sums += i;
            }
            return (long) (sums % 32);
        } else {
            return RandomUtils.nextLong(0, 31);
        }
    }


    @Override
    public Comparable<?> generateKey() {
        synchronized (ShardingSnowFlakeKeyGenerator.class) {
            long currTimestamp = getTimestamp();
            if (currTimestamp < lastTimestamp) {
                throw new IllegalStateException("Clock moved backwards.  Refusing to generate id");
            } else {
                if (currTimestamp == lastTimestamp) {
                    sequence = (sequence + 1L) & MAX_SEQUENCE;
                    if (sequence == 0L) {
                        currTimestamp = this.getNextMill();
                    }
                } else {
                    sequence = RandomUtils.nextLong() % 2; // avoid always 0
                }

                lastTimestamp = currTimestamp;
                final long generatedKey = (currTimestamp - START_TIMESTAMP << TIMESTAMP_LEFT) | (this.datacenterId << DATACENTER_LEFT)
                        | (this.machineId << MACHINE_LEFT) | sequence;
//                System.out.printf("generate key: %d%n", generatedKey);
                return generatedKey;
            }
        }
    }

    @Override
    public String getType() {
        return ALGORITHM_NAME;
    }

    @Override
    public void setInstanceContext(InstanceContext instanceContext) {

    }

    @Override
    public boolean isDefault() {
        return true;
    }

    private long getNextMill() {
        long millisecond;
        for (millisecond = getTimestamp(); millisecond <= lastTimestamp; millisecond = getTimestamp()) {
        }

        return millisecond;
    }

    private long getTimestamp() {
        return System.currentTimeMillis() + offsetTimestamp;
    }

}
