package com.zhegu.core.frameworkcore.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by ZHIQIANG LI on 2017/12/10.
 */
@Configuration
@MapperScan(basePackages = "${spring.mybatis.package}",sqlSessionFactoryRef = "sqlSessionFactory")
@Import(DataSourceConfig.class)
public class MybatisConfig {
    @Value("${spring.mybatis.package}")
    private String packageName;
    @Value("${spring.mybatis.mapper_location}")
    private String mapper_localtion;
    @Value("${spring.mybatis.config_localtion}")
    private String config_localtion;

    /**
     * mybatis sql session工厂
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactory")
    @Primary
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource ds) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapper_localtion));
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource(config_localtion));
        return sessionFactory.getObject();
    }
}
