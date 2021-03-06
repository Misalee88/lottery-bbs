package com.zhegu.lottery.bbs.orm;

import java.io.Serializable;

public class WbLike implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wb_like.GUID
     *
     * @mbg.generated
     */
    private Integer guid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wb_like.WB_ID
     *
     * @mbg.generated
     */
    private Integer wbId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wb_like.USER_ACCOUNT
     *
     * @mbg.generated
     */
    private String userAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wb_like
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wb_like
     *
     * @mbg.generated
     */
    public WbLike(Integer guid, Integer wbId, String userAccount) {
        this.guid = guid;
        this.wbId = wbId;
        this.userAccount = userAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wb_like
     *
     * @mbg.generated
     */
    public WbLike() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wb_like.GUID
     *
     * @return the value of wb_like.GUID
     *
     * @mbg.generated
     */
    public Integer getGuid() {
        return guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wb_like.GUID
     *
     * @param guid the value for wb_like.GUID
     *
     * @mbg.generated
     */
    public void setGuid(Integer guid) {
        this.guid = guid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wb_like.WB_ID
     *
     * @return the value of wb_like.WB_ID
     *
     * @mbg.generated
     */
    public Integer getWbId() {
        return wbId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wb_like.WB_ID
     *
     * @param wbId the value for wb_like.WB_ID
     *
     * @mbg.generated
     */
    public void setWbId(Integer wbId) {
        this.wbId = wbId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wb_like.USER_ACCOUNT
     *
     * @return the value of wb_like.USER_ACCOUNT
     *
     * @mbg.generated
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wb_like.USER_ACCOUNT
     *
     * @param userAccount the value for wb_like.USER_ACCOUNT
     *
     * @mbg.generated
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }
}