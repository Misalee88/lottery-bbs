package com.zhegu.core.frameworkcore.config;

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
@EnableConfigurationProperties({MybatisProperties.class})
public class DataSourceConfig {

    @Autowired
    private MybatisProperties properties;

    @Bean(name="dataSource")
    @Autowired
    @Primary
    public DataSource dataSource(Environment env){
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties properties = build(env,"spring.datasource.druid.dataSorce.");
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

    private Properties build(Environment env, String prefix) {

        Properties prop = new Properties();
        prop.put("url", env.getProperty(prefix + "url"));
        prop.put("username", env.getProperty(prefix + "username"));
        prop.put("password", env.getProperty(prefix + "password"));
        prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
        prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
        prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
        prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
        prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));

        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));

        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
        prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
        prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
        prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis",
                env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
        prop.put("filters", env.getProperty(prefix + "filters"));

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
        sessionFactory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(properties.getMapperLocation()));
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
                .getResource(properties.getConfigLocation()));
//        sessionFactory.setTypeAliasesPackage("com.zhegu.lottery.bbs.orm");
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/mysql/*.xml"));
//        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver()
//                .getResource("classpath:mybatis.xml"));
        return sessionFactory.getObject();
    }

}