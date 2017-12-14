package com.zhegu.core.frameworkcore.config;

import com.zhegu.core.frameworkcore.properties.MybatisProperties;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZHIQIANG LI on 2017/12/10.
 */
@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@EnableConfigurationProperties({MybatisProperties.class})
public class MybatisConfig {
    @Autowired
    private MybatisProperties mybatisProperties;

    @Bean(name="mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage(mybatisProperties.getMapperPackage());
        mapperScannerConfigurer.setBasePackage("com.zhegu.lottery.bbs.dao");
        return mapperScannerConfigurer;
    }
}
