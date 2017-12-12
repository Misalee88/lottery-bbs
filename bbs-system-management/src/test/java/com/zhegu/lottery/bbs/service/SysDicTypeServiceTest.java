package com.zhegu.lottery.bbs.service;

import com.zhegu.lottery.bbs.orm.SysDictType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysDicTypeServiceTest {
	@Resource
	private SysDicTypeService sysDicTypeService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetById(){
		Integer id = 0;
		SysDictType sysDictType = sysDicTypeService.getById(id);

		System.out.println(sysDictType != null ? sysDictType.getName():"");
	}

}
