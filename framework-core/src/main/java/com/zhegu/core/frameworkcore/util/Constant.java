package com.zhegu.core.frameworkcore.util;

/**
 * 常量
 * 
 * @author 张良
 * @email zhang.l1@haihangyun.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
	/** 超级管理员ID */
	public static final String SUPER_ADMIN = "1";

	/**
	 * 菜单类型
	 * 
	 * @author 张良
	 * @email zhang.l1@haihangyun.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author 张良
     * @email zhang.l1@haihangyun.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 统计指标
     */
    public class StaType{

        //统计指标（0 进港航班、1离港航班、2 出港旅客、3 进港旅客、4 出港延误旅客、5 进港货邮重量、
        // 6 财务航空性收入、7 国际进港航班 、8  国际进港旅客、9 国际货物重量、10非航空性收入、
        // 11国际离港航班、12国际离港旅客、13 出港货邮重量、14 进港国际货邮重量、15 出港国际货邮重量）
        /**
         * 进港航班
         */
        public final static String STA_0 = "0";
        /**
         * 离港航班
         */
        public final static String STA_1 = "1";
        /**
         * 出港旅客
         */
        public final static String STA_2 = "2";
        /**
         * 进港旅客
         */
        public final static String STA_3 = "3";
        /**
         * 出港延误旅客
         */
        public final static String STA_4 = "4";
        /**
         * 货邮重量
         */
        public final static String STA_5 = "5";
        /**
         * 财务航空性收入
         */
        public final static String STA_6 = "6";
        /**
         * 国际进港航班
         */
        public final static String STA_7 = "7";
        /**
         * 国际进港旅客
         */
        public final static String STA_8 = "8";
        /**
         * 国际货邮重量
         */
        public final static String STA_9 = "9";
        /**
         * 非航空行收入
         */
        public final static String STA_10 = "10";
        /**
         * 国际离港航班
         */
        public final static String STA_11 = "11";
        /**
         * 国际离港旅客
         */
        public final static String STA_12 = "12";
        /**
         * 出港货邮重量
         */
        public final static String STA_13 = "13";
        /**
         * 进港国际货邮重量
         */
        public final static String STA_14 = "14";
        /**
         * 出港国际货邮重量
         */
        public final static String STA_15 = "15";
        /**
         * 出港延误航班
         */
        public final static String STA_16 = "16";

    }

    /**
     * 地区名称
     */
    public class Area{
        public final static String ZN = "ZN";
        public final static String ZN_NAME = "中南";
        public final static String HD = "HD";
        public final static String HD_NAME = "华东";
        public final static String XN = "XN";
        public final static String XN_NAME = "西南";
        public final static String HB = "HB";
        public final static String HB_NAME = "华北";
        public final static String DB = "DB";
        public final static String DB_NAME = "东北";
        public final static String XB = "XB";
        public final static String XB_NAME = "西北";
        public final static String GJ = "GJ";
        public final static String GJ_NAME = "国际";
    }
}
