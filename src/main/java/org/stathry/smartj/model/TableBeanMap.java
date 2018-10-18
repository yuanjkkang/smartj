package org.stathry.smartj.model;

import java.util.List;

/**
 * 表的基本信息映射为bean
 * 
 * @author dongdaiming
 */
public class TableBeanMap {

	private String simpleClassName;

	private String className;

	private String table;

	private String desc;

	private List<ColumnFieldMap> fields;
	private List<ColumnFieldMap> insertFields;

    private String idType;
    private String idJdbcType;

    @Override
    public String toString() {
        return "TableBeanMap{" +
                "simpleClassName='" + simpleClassName + '\'' +
                ", className='" + className + '\'' +
                ", table='" + table + '\'' +
                ", desc='" + desc + '\'' +
                ", fields=" + fields +
                ", idType='" + idType + '\'' +
                ", idJdbcType='" + idJdbcType + '\'' +
                '}';
    }

    public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSimpleClassName() {
		return simpleClassName;
	}

	public void setSimpleClassName(String simpleClassName) {
		this.simpleClassName = simpleClassName;
	}

	public List<ColumnFieldMap> getFields() {
		return fields;
	}

	public void setFields(List<ColumnFieldMap> fields) {
		this.fields = fields;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdJdbcType() {
        return idJdbcType;
    }

    public void setIdJdbcType(String idJdbcType) {
        this.idJdbcType = idJdbcType;
    }

    public List<ColumnFieldMap> getInsertFields() {
        return insertFields;
    }

    public void setInsertFields(List<ColumnFieldMap> insertFields) {
        this.insertFields = insertFields;
    }
}
