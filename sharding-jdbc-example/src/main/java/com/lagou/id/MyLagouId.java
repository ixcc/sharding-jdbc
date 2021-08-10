package com.lagou.id;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;

public class MyLagouId implements ShardingKeyGenerator {

    SnowflakeShardingKeyGenerator snow = new SnowflakeShardingKeyGenerator();


    @Override
    public Comparable<?> generateKey() {
        System.out.println("-----自定义主键生成器id------");
        return snow.generateKey();
    }

    @Override
    public String getType() {
        return "LAGOUKEY";
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
