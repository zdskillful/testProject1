package com.actec.mocktest.modules.mock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author zd
 * @Date 2019/7/25 15:53
 */
@TableName("m_project")
public class SysProject {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId
    private Long id;

    private Long projectId;

    private String projectName;

    private String tableName;

    private String colComment;

    private String dbName;

    private String chargePersonName;

    private String heatDegree;

    private Date createTime;

    @TableField(exist = false)
    private List<Map<String,Object>> catalogName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColComment() {
        return colComment;
    }

    public void setColComment(String colComment) {
        this.colComment = colComment;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getChargePersonName() {
        return chargePersonName;
    }

    public void setChargePersonName(String chargePersonName) {
        this.chargePersonName = chargePersonName;
    }

    public String getHeatDegree() {
        return heatDegree;
    }

    public void setHeatDegree(String heatDegree) {
        this.heatDegree = heatDegree;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Map<String, Object>> getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(List<Map<String, Object>> catalogName) {
        this.catalogName = catalogName;
    }
}
