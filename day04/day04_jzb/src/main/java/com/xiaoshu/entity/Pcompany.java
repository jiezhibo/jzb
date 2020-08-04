package com.xiaoshu.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "p_company")
public class Pcompany implements Serializable {
    @Id
    @Column(name = "company_id")
    private Integer companyid;

    @Column(name = "company_name")
    private String companyname;

    private static final long serialVersionUID = 1L;

    /**
     * @return company_id
     */
    public Integer getCompanyid() {
        return companyid;
    }

    /**
     * @param companyId
     */
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    /**
     * @return company_name
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyName
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", companyid=").append(companyid);
        sb.append(", companyname=").append(companyname);
        sb.append("]");
        return sb.toString();
    }
}