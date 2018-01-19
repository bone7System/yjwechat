package com.yj.domain.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tao.huang on 2017/5/15.
 */
@Entity
public class OrganizationInfo implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "pid")
    private Long pid;
    @Column(name = "orgname")
    private String orgname;
    @Column(name = "orgdesc")
    private String orgdesc;
    @Column(name = "orgcode")
    private String orgcode;
    @Column(name = "keyword")
    private String keyword;
    @Column(name = "status")
    private Long status;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date create_time;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date update_time;
    @Column(name = "leaderids")
    private String leaderIds;
    @Column(name = "leadernames")
    private String leaderNames;
    @Column(name = "adminids")
    private String adminIds;
    @Column(name = "adminnames")
    private String adminNames;
    @Column(name = "chargehandids")
    private String chargehandIds;
    @Column(name = "chargehandnames")
    private String chargehandNames;

    @Column(name = "appid")
    String appid;//
    @Column(name = "personCount")
    Integer personCount;//人数

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public String getLeaderNames() {
        return leaderNames;
    }

    public void setLeaderNames(String leaderNames) {
        this.leaderNames = leaderNames;
    }


    public String getChargehandNames() {
        return chargehandNames;
    }

    public void setChargehandNames(String chargehandNames) {
        this.chargehandNames = chargehandNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOrgdesc() {
        return orgdesc;
    }

    public void setOrgdesc(String orgdesc) {
        this.orgdesc = orgdesc;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }


    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getLeaderIds() {
        return leaderIds;
    }

    public void setLeaderIds(String leaderIds) {
        this.leaderIds = leaderIds;
    }

    public String getAdminIds() {
        return adminIds;
    }

    public void setAdminIds(String adminIds) {
        this.adminIds = adminIds;
    }

    public String getAdminNames() {
        return adminNames;
    }

    public void setAdminNames(String adminNames) {
        this.adminNames = adminNames;
    }

    public String getChargehandIds() {
        return chargehandIds;
    }

    public void setChargehandIds(String chargehandIds) {
        this.chargehandIds = chargehandIds;
    }
}
