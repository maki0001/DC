package co.jp.jbcc.dc.data.creator;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class DateDataCreator extends CreatorBase {

	@Override
	public String createTextData(DataSchema dataSchema) {

		result = "9999/99/99";
		if( dataSchema == null ) return result;

//		String[] options = dataSchema.getOption();
//		result = new SimpleDateFormat(options[0]).format(new Date());
		return result;
	}

}
