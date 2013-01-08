package co.jp.jbcc.dc.data.schema;

import java.util.List;

public class DataSchema{

	private String header;
	private String type;
	private int size;
	private List option;

	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List getOption() {
		return option;
	}
	public void setOption(List option) {
		this.option = option;
	}
}
