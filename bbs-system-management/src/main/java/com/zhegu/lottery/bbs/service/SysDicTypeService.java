package com.zhegu.lottery.bbs.service;

import com.zhegu.lottery.bbs.dao.SysDictTypeMapper;
import com.zhegu.lottery.bbs.orm.SysDictType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ZHIQIANG LI on 2017/12/11.
 */
@Service
public class SysDicTypeService {
    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    public SysDictType getById(Integer id){
        SysDictType sysDictType = sysDictTypeMapper.selectByPrimaryKey(id);

        return sysDictType;
    }
}
