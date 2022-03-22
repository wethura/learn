package com.wethura.learn.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author sola
 */
public class ConfigServiceTest {

    @Test
    public void testListenerConfig() throws NacosException, InterruptedException {
        final Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.NAMESPACE, "app1");
        properties.setProperty(PropertyKeyConst.USERNAME, "app1");
        properties.setProperty(PropertyKeyConst.PASSWORD, "test");
        properties.setProperty(PropertyKeyConst.IS_USE_CLOUD_NAMESPACE_PARSING, "false");
        properties.setProperty(PropertyKeyConst.IS_USE_ENDPOINT_PARSING_RULE, "false");
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");

        final ConfigService configService = NacosFactory.createConfigService(properties);

        final String config = configService.getConfig("cse_springclougconfig_example-default", Constants.DEFAULT_GROUP,
                1000);

        configService.addListener("cse_springclougconfig_example-default", Constants.DEFAULT_GROUP,
                new AbstractConfigChangeListener() {
                    @Override
                    public void receiveConfigChange(ConfigChangeEvent event) {
                        System.out.println("Receive at " + LocalDateTime.now());
                        event.getChangeItems().forEach(
                                i -> System.out.println(i.getKey() + ":" + i.getNewValue() + "/" + i.getOldValue()));
                    }
                });

        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
