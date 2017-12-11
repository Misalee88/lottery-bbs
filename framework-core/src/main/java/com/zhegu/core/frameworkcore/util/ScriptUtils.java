package com.zhegu.core.frameworkcore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.*;
import java.io.Reader;
import java.util.Date;
import java.util.Map;

/**
 * Created by ZHIQIANG LI on 2017/10/21.
 *
 * javaScript公式计算
 */
public class ScriptUtils{
    private static Logger logger = LoggerFactory.getLogger(ScriptUtils.class);
    private static  ScriptEngineManager factory = new ScriptEngineManager();
    private static ScriptEngine engine = factory.getEngineByName("JavaScript");

    public static Object eval(String script) throws ScriptException {
        return engine.eval(script);
    }

    public static Object eval(Reader reader) throws ScriptException {
        return engine.eval(reader);
    }

    public static Object eval(Reader reader,Bindings bindings) throws ScriptException {
        return engine.eval(reader,bindings);
    }

    public static Object eval(String script,Bindings bindings) throws ScriptException {
        return  engine.eval(script,bindings);
    }

    public static Object eval(String script,ScriptContext context) throws ScriptException {
        return engine.eval(script, context);
    }

    public static Object eval(Reader reader,ScriptContext context) throws ScriptException {
        return engine.eval(reader, context);
    }

    /**
     * @Description: 根据规则及参数集合返回规则匹配结果
     * @param script 规则  script   num1 + num2
     * @param entity 参数key value集合 {'num1':10,'num2':20}
     * @return java.lang.Object 30
     * @author ZHIQIANG LI
     * @date 2017/10/23 10:45
     **/
    public static Object eval(String script,Object entity) throws ScriptException {
        engine = factory.getEngineByName("JavaScript");
        String json = JsonUtils.toStringIncludeNull(entity);
        Map<String,Object> map = JsonUtils.toObject(json,Map.class);
        for(String key : map.keySet()){
            if(engine.get(key) == null){
                engine.put(key,map.get(key));
            }
        }
        return engine.eval(script);
    }

    /**
     * @Description:  根据规则，参数集合，时间key，计算时间类型获取计算后的时间
     * @param script 计算规则  TOBT-AODB-10
     * @param entity 参数实体 {TOBT:2017-10-23 15:50:00,AODB:30}
     * @param dateKey  TOBT
     * @param timeType mi 分钟
     * @return java.util.Date  2017-10-23 15:10:00
     * @author ZHIQIANG LI
     * @date 2017/10/23 15:23
     **/
    public static Date evelDate(String script,Object entity,String dateKey,String timeType) throws ScriptException {
//        logger.info("初始时间逻辑公式："+script);
        engine = factory.getEngineByName("JavaScript");
        String json = JsonUtils.toStringIncludeNull(entity);
        Map<String,Object> map = JsonUtils.toObject(json,Map.class);
        for(String key : map.keySet()){
            if(map.get(key)!=null){
                String value = map.get(key).toString();
                Date date = DateUtils.parse(value,DateUtils.SDF_YMDHMS);
                if(date != null){
                    if(DateUtils.YYYY.equals(timeType)){//年
                        script = script.replaceAll(key,"new Date('"+value+"').getYear()");
                    }else if(DateUtils.MM.equals(timeType)){//月
                        script = script.replaceAll(key,"new Date('"+value+"').getMonth()");
                    }else if(DateUtils.DD.equals(timeType)){//日
                        script = script.replaceAll(key,"new Date('"+value+"').getDate()");
                    }else if(DateUtils.HH.equals(timeType)){//小时
                        script = script.replaceAll(key,"new Date('"+value+"').getHour()");
                    }else if(DateUtils.MI.equals(timeType)){//分
                        script = script.replaceAll(key,"new Date('"+value+"').getMinutes()");
                    }else if(DateUtils.SS.equals(timeType)){//秒
                        script = script.replaceAll(key,"new Date('"+value+"').getSeconds()");
                    }
                    if(dateKey == null){
                        dateKey = key;
                    }
                }else{
                    script = script.replaceAll(key,value);
                }
            }
        }
//        logger.info("时间计算逻辑公式："+script);
        Object obj = engine.eval(script);

        StringBuffer rule = new StringBuffer();
        rule.append(" new Date('"+map.get(dateKey).toString()+"')");
        rule.append(".setTime(new Date('"+map.get(dateKey).toString()+"').");

        Long l = Double.valueOf(obj.toString()).longValue();
        if(DateUtils.YYYY.equals(timeType)){//年
            rule.append("setYear("+l+")) ");
        }else if(DateUtils.MM.equals(timeType)){//月
            rule.append("getMonth("+l+")) ");
        }else if(DateUtils.DD.equals(timeType)){//日
            rule.append("getDate("+l+")) ");
        }else if(DateUtils.HH.equals(timeType)){//小时
            rule.append("setHour("+l+")) ");
        }else if(DateUtils.MI.equals(timeType)){//分
            rule.append("setMinutes("+l+")) ");
        }else if(DateUtils.SS.equals(timeType)){//秒
            rule.append("getSeconds("+l+")) ");
        }
        Object dateObj = engine.eval(rule.toString());
//        logger.info("时间结果逻辑公式："+rule.toString());

        return new Date(Double.valueOf(dateObj.toString()).longValue());
    }
}
