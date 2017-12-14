package com.zhegu.core.frameworkcore.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

/**
 * Created by ZHIQIANG LI on 2017/12/14.
 */

@Getter
@Setter
@ConfigurationProperties(prefix="spring.datasource.druid.dataSource")
@Primary
public class DataSourceProperties {

    private String name;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Integer maxWait;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private String validationQuery;
    private Integer validationQueryTimeout;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;

}
