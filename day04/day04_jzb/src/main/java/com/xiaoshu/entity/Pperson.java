package com.xiaoshu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "p_person")
public class Pperson implements Serializable {
    @Id
    private Integer id;

    @Column(name = "p_name")
    private String pName;

    private String gender;

    @Column(name = "company_id")
    private Integer companyid;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date datime;
    @Transient
    private Pcompany pcompany;
    public Pcompany getPcompany() {
		return pcompany;
	}

	public void setPcompany(Pcompany pcompany) {
		this.pcompany = pcompany;
	}

	private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return p_name
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pName=").append(pName);
        sb.append(", gender=").append(gender);
        sb.append(", companyid=").append(companyid);
        sb.append(", datime=").append(datime);
        sb.append("]");
        return sb.toString();
    }
}