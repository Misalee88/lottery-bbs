package com.zhegu.core.frameworkcore.mybatils;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * Created by ZHIQIANG LI on 2017/8/30.
 * mybatis查询结果为Map时，将key按驼峰原则转化
 * 将该类配置在mybatis.xml，typeHandlers 和 plugins标签之间， 格式：
 *
   <objectWrapperFactory type="com.airport.kernel.mybatis.MapWrapperFactory"/>
 */
public class MapWrapperFactory implements ObjectWrapperFactory{
    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object o) {
        return new MybatisMapWrapper(metaObject,(Map)o);
    }
}
