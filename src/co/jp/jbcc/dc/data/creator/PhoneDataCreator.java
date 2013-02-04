package co.jp.jbcc.dc.data.creator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class PhoneDataCreator extends CreatorBase {

	private StringBuilder sb;
	private static final List<String> formats = new ArrayList<String>();
	static{
		formats.add("xxxx-xx-xxxx");
		formats.add("xxx-xx-xxxx");
		formats.add("xxx-xxxx-xxxx");
	}

	public PhoneDataCreator(){
		sb = new StringBuilder();
		if( formats.isEmpty() ){
			formats.add("xxxx-xx-xxxx");
			formats.add("xxx-xx-xxxx");
			formats.add("xxx-xxxx-xxxx");
		}
	}

	@Override
	public String createTextData(DataSchema dataSchema) {

		result = "";
		if( dataSchema == null ) return result;
		if( sb != null ) sb.setLength(0);

		String format = formats.get( Integer.parseInt(RandomStringUtils.random(1, "012")) );
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i<format.length(); i++){
			if( format.substring(i, i+1).equals("x") ){
				sb.append( Math.round(Math.random() * 9) );
			}else{
				sb.append("-");
			}
		}

		return sb.toString();
	}

}
