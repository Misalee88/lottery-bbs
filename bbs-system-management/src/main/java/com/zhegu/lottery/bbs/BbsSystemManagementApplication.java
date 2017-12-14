package com.zhegu.lottery.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhegu.lottery.bbs.dao")
public class BbsSystemManagementApplication{

	public static void main(String[] args) {
		SpringApplication.run(BbsSystemManagementApplication.class, args);
	}
}
