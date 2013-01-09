package co.jp.jbcc.dc.dto;

import java.util.Date;

import co.jp.jbcc.dc.annotation.Bind;

public class DataSchemaDto {

	@Bind(name="ID")
	private int id;

	@Bind(name="FILE_NAME")
	private String fileName;

	@Bind(name="SCHEMA_TEXT")
	private String schemaText;

	@Bind(name="MEMO")
	private String memo;

	@Bind(name="CREATE_DATE")
	private Date createDate;

	@Bind(name="UPDATE_DATE")
	private Date updateDate;

	public DataSchemaDto(){

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSchemaText() {
		return schemaText;
	}
	public void setSchemaText(String schemaText) {
		this.schemaText = schemaText;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
