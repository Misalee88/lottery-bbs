package com.zhegu.lottery.bbs.config;

//import com.zhegu.lottery.bbs.properties.MybatisPeoperties;
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
//@EnableConfigurationProperties({MybatisPeoperties.class})
public class MybatisConfig {
//    @Autowired
//    private MybatisPeoperties peoperties;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage(peoperties.getMapperPackage());
        mapperScannerConfigurer.setBasePackage("com.zhegu.lottery.bbs.dao");
        return mapperScannerConfigurer;
    }
}
