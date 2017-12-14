package com.zhegu.core.frameworkcore.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

/**
 * Created by ZHIQIANG LI on 2017/12/11.
 */
@Getter
@Setter
@ConfigurationProperties(prefix="spring.mybatis")
@Primary
public class MybatisProperties {

    private String mapperPackage;//mapperPackage
    private String mapperLocation;//mapperLocation
    private String configLocation;//configLocation
    private String typeAliasesPackage;//typeAliasesPackage

}
