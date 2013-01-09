package co.jp.jbcc.dc.data.creator;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class ZipDataCreator extends CreatorBase {

	private StringBuilder sb;
	private static final String format = "xxx-xxxx";

	public ZipDataCreator(){
		sb = new StringBuilder();
	}

	@Override
	public String createTextData(DataSchema dataSchema) {

		result = "";
		if( dataSchema == null ) return result;
		if( sb != null ) sb.setLength(0);

		for( int i = 0; i<format.length(); i++){
			if( format.substring(i, i+1).equals("x") ){
				sb.append( Math.round(Math.random() * 10) );
			}else{
				sb.append("-");
			}
		}

		return sb.toString();
	}

}
