package org.stathry.smartj.model;

/**
 * ColumnFieldMap
 * 
 * @author dongdaiming
 */
public class ColumnFieldMap {

	private String name;

	private String column;

	private String type;

	private String jdbcType;

	private String comment;
	private boolean priKey;

	public ColumnFieldMap() {
		super();
	}

    public ColumnFieldMap(String name, String column, String type, String jdbcType, String comment, boolean isKey) {
        this.name = name;
        this.column = column;
        this.type = type;
        this.jdbcType = jdbcType;
        this.comment = comment;
        this.priKey = isKey;
    }

    @Override
    public String toString() {
        return "ColumnFieldMap{" +
                "name='" + name + '\'' +
                ", column='" + column + '\'' +
                ", type='" + type + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", comment='" + comment + '\'' +
                ", priKey=" + priKey +
                '}';
    }

    public String getName() {
		return name;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public boolean isPriKey() {
        return priKey;
    }

    public void setPriKey(boolean priKey) {
        this.priKey = priKey;
    }
}
