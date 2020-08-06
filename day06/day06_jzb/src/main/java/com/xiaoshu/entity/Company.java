package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "express_company")
public class Company implements Serializable {
    @Id
    @Column(name = "express_type_id")
    private Integer expressTypeId;

    @Column(name = "express_name")
    private String expressName1;

    private String status;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return express_type_id
     */
    public Integer getExpressTypeId() {
        return expressTypeId;
    }

    /**
     * @param expressTypeId
     */
    public void setExpressTypeId(Integer expressTypeId) {
        this.expressTypeId = expressTypeId;
    }

    /**
     * @return express_name
     */
    public String getExpressName1() {
        return expressName1;
    }

    /**
     * @param expressName
     */
    public void setExpressName1(String expressName1) {
        this.expressName1 = expressName1 == null ? null : expressName1.trim();
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", expressTypeId=").append(expressTypeId);
        sb.append(", expressName1=").append(expressName1);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}