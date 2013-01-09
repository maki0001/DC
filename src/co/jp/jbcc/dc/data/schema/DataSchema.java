package co.jp.jbcc.dc.data.schema;

import java.util.List;

import net.arnx.jsonic.JSON;

import org.apache.commons.lang3.StringUtils;

public class DataSchema{

	private String header;
	private String type;
	private int size;
	private List<String> option;

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
	public List<String> getOption() {
		return option;
	}
	public void setOption(List<String> option) {
		this.option = option;
	}

	public static DataSchema[] getDataSchema( String schemaText ){

		if( StringUtils.isEmpty(schemaText) ) return null;

		DataSchema[] dataSchemas = JSON.decode( schemaText, DataSchema[].class);
		for( DataSchema dataSchema : dataSchemas ){
			System.out.print( dataSchema.getType() + "\t");
			System.out.print( dataSchema.getSize() + "\t");
			System.out.print( dataSchema.getOption() + "\n");
		}
		return dataSchemas;
	}
}
