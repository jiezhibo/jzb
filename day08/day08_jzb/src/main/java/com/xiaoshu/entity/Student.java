package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Student implements Serializable {
    @Id
    private Integer sid;

    private String sname;

    private String sex;

    private Integer age;

    private Date datime;

    private Integer scid;

    private static final long serialVersionUID = 1L;

    /**
     * @return sid
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * @param sid
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * @return sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * @param sname
     */
    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return datime
     */
    public Date getDatime() {
        return datime;
    }

    /**
     * @param datime
     */
    public void setDatime(Date datime) {
        this.datime = datime;
    }

    /**
     * @return scid
     */
    public Integer getScid() {
        return scid;
    }

    /**
     * @param scid
     */
    public void setScid(Integer scid) {
        this.scid = scid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", sname=").append(sname);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", datime=").append(datime);
        sb.append(", scid=").append(scid);
        sb.append("]");
        return sb.toString();
    }
}