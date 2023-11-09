package com.wethura.rabbitmq.vo;

import com.alibaba.fastjson.JSON;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author sola
 **/
public class RabbitmqEchoVo {

    private String name;
    private String kind;
    private String randomCode;
    private long timestamp;
    private String serialNumber;

    public RabbitmqEchoVo() {
    }


    public RabbitmqEchoVo(String name, String kind) {
        this.name = name;
        this.kind = kind;

        this.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        this.serialNumber = SerialNumberTools.nextNumber();
        this.randomCode = RandomStringUtils.randomAlphabetic(15, 20);
    }

    public static RabbitmqEchoVo parse(String json) {
        return JSON.parseObject(json, RabbitmqEchoVo.class);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private static class SerialNumberTools {

        private static AtomicInteger numRegistry = new AtomicInteger(0);

        public synchronized static String nextNumber() {
            return String.format("serial-%d", numRegistry.incrementAndGet());
        }
    }
}
