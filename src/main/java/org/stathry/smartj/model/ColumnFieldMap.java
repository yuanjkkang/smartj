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

	public ColumnFieldMap() {
		super();
	}

	public ColumnFieldMap(String name, String collumn, String type, String comment) {
		this.name = name;
		this.column = collumn;
		this.type = type;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ColumnFieldMap [name=" + name + ", type=" + type + ", comment=" + comment + "]";
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
}
