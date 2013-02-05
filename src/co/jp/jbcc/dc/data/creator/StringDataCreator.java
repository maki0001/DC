package co.jp.jbcc.dc.data.creator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import co.jp.jbcc.dc.data.schema.DataSchema;

/**
 *
 * @author J32200
 * string option:[word type, prifix word]
 */
public class StringDataCreator extends CreatorBase {

	private enum OPTION_FORMAT{ alphabetic, ascii, alphanumeric, numeric }
	private String format;
	private String prifixWord;

	@Override
	public String createTextData( DataSchema dataSchema ) {

		result = "";
		if( dataSchema == null ) return result;

		if( StringUtils.isEmpty(format) ) format = dataSchema.getOption().get(0);
		if( StringUtils.isEmpty(prifixWord) ) prifixWord = dataSchema.getOption().get(1);

		if( format.equals( OPTION_FORMAT.alphabetic.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomAlphabetic(dataSchema.getSize());
		}else if( format.equals( OPTION_FORMAT.ascii.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomAscii(dataSchema.getSize());
		}else if( format.equals( OPTION_FORMAT.alphanumeric.toString()) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomAlphanumeric(dataSchema.getSize());
		}else if( format.equals( OPTION_FORMAT.numeric) ){
			result = dataSchema.getOption().get(1) + RandomStringUtils.randomNumeric(dataSchema.getSize());
		}

		if( ! StringUtils.isEmpty(prifixWord) ){
			return StringUtils.join( prifixWord, result );
		}

		return result;
	}

}
