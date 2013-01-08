package co.jp.jbcc.dc.data.creator;

import org.apache.commons.lang3.RandomStringUtils;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class StringDataCreator extends CreatorBase {

	private enum OPTION_FORMAT{ number, alphabetic, ascii, alphanumeric, numeric }

	@Override
	public String createTextData( DataSchema dataSchema ) {

		result = "";
		if( dataSchema == null ) return result;

		String format = (String) dataSchema.getOption().get(0);
		if( format.equals( OPTION_FORMAT.number.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.random(dataSchema.getSize(), "0123456789");
		}else if( format.equals( OPTION_FORMAT.alphabetic.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomAlphabetic(dataSchema.getSize());
		}else if( format.equals( OPTION_FORMAT.ascii.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomAscii(dataSchema.getSize());
		}else if( format.equals( OPTION_FORMAT.alphanumeric.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomAlphanumeric(dataSchema.getSize());
		}else if( format.equals( OPTION_FORMAT.numeric) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomNumeric(dataSchema.getSize());
		}
		return result;
	}

}
