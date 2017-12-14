package com.zhegu.core.frameworkcore.config;

import com.zhegu.core.frameworkcore.properties.DataSourceProperties;
import com.zhegu.core.frameworkcore.properties.MybatisProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ZHIQIANG LI on 2017/12/10.
 * 数据源配置
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({MybatisProperties.class, DataSourceProperties.class})
public class DataSourceConfig {

    @Autowired
    private MybatisProperties mybatisProperties;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name="dataSource")
    @Autowired
    @Primary
    public DataSource dataSource(Environment env){
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties properties = build();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("dataSource");
        ds.setPoolSize(5);
        ds.setXaProperties(properties);

        return ds;
    }

    @Bean("JdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource ds){
        return  new JdbcTemplate(ds);
    }

    private Properties build() {

        Properties prop = new Properties();
        prop.put("url", dataSourceProperties.getUrl());
        prop.put("username", dataSourceProperties.getUsername());
        prop.put("password", dataSourceProperties.getPassword());
        prop.put("driverClassName", dataSourceProperties.getDriverClassName());
        prop.put("initialSize", dataSourceProperties.getInitialSize());
        prop.put("maxActive", dataSourceProperties.getMaxActive());
        prop.put("minIdle", dataSourceProperties.getMinIdle());
        prop.put("maxWait", dataSourceProperties.getMaxWait());
        prop.put("poolPreparedStatements", dataSourceProperties.isPoolPreparedStatements());

        prop.put("maxPoolPreparedStatementPerConnectionSize",dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());

        prop.put("validationQuery", dataSourceProperties.getValidationQuery());
        prop.put("validationQueryTimeout", dataSourceProperties.getValidationQueryTimeout());
        prop.put("testOnBorrow", dataSourceProperties.isTestOnBorrow());
        prop.put("testOnReturn", dataSourceProperties.isTestOnReturn());
        prop.put("testWhileIdle", dataSourceProperties.isTestWhileIdle());
        prop.put("timeBetweenEvictionRunsMillis",dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        prop.put("minEvictableIdleTimeMillis", dataSourceProperties.getMinEvictableIdleTimeMillis());
        prop.put("filters", dataSourceProperties.getFilters());

        return prop;
    }

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
        sessionFactory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mybatisProperties.getMapperLocation()));
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource(mybatisProperties.getConfigLocation()));
//        sessionFactory.setTypeAliasesPackage("com.zhegu.lottery.bbs.orm");
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/mysql/*.xml"));
//        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
//                .getResource("classpath:mybatis.xml"));
        return sessionFactory.getObject();
    }

}
