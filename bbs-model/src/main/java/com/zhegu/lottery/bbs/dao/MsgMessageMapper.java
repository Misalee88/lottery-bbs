package com.zhegu.lottery.bbs.dao;

import com.zhegu.lottery.bbs.orm.MsgMessage;

public interface MsgMessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msg_message
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msg_message
     *
     * @mbg.generated
     */
    int insert(MsgMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msg_message
     *
     * @mbg.generated
     */
    int insertSelective(MsgMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msg_message
     *
     * @mbg.generated
     */
    MsgMessage selectByPrimaryKey(Integer guid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msg_message
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MsgMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table msg_message
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MsgMessage record);
}