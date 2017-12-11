package com.zhegu.core.frameworkcore;

import com.zhegu.core.frameworkcore.config.DataSourceConfig;
import com.zhegu.core.frameworkcore.config.MybatisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataSourceConfig.class, MybatisConfig.class})
public class FrameworkCoreApplication {

}
