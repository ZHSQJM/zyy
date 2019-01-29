package com.kinglian.screeninquiry.model.entity;

import java.io.Serializable;

/**
 * com_department
 * @author 
 */
public class ComDepartment implements Serializable {
    private Integer id;

    private String departname;

    private String description;

    private String parentName;

    private Boolean forRegister;

    private Integer parentId;

    private static final long serialVersionUID = 1L;

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getForRegister() {
        return forRegister;
    }

    public void setForRegister(Boolean forRegister) {
        this.forRegister = forRegister;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ComDepartment other = (ComDepartment) that;
        return (this.getid() == null ? other.getid() == null : this.getid().equals(other.getid()))
            && (this.getDepartname() == null ? other.getDepartname() == null : this.getDepartname().equals(other.getDepartname()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getParentName() == null ? other.getParentName() == null : this.getParentName().equals(other.getParentName()))
            && (this.getForRegister() == null ? other.getForRegister() == null : this.getForRegister().equals(other.getForRegister()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getid() == null) ? 0 : getid().hashCode());
        result = prime * result + ((getDepartname() == null) ? 0 : getDepartname().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getParentName() == null) ? 0 : getParentName().hashCode());
        result = prime * result + ((getForRegister() == null) ? 0 : getForRegister().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departname=").append(departname);
        sb.append(", description=").append(description);
        sb.append(", parentName=").append(parentName);
        sb.append(", forRegister=").append(forRegister);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}