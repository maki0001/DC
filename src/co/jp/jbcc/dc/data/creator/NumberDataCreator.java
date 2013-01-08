package co.jp.jbcc.dc.data.creator;

import org.apache.commons.lang3.RandomStringUtils;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class NumberDataCreator extends CreatorBase {

	@Override
	public String createTextData(DataSchema dataSchema) {

		result = "0";
		if( dataSchema == null ) return result;

		result = RandomStringUtils.random( dataSchema.getSize(), "123456789" );
		return result;
	}

}
