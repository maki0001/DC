package co.jp.jbcc.dc.data.creator;

import org.apache.commons.lang3.StringUtils;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class NumberDataCreator extends CreatorBase {

	private enum OPTION_FORMAT { random, cycle }
	private int startNumber;
	private int endNumber;

	public NumberDataCreator(){
		startNumber = 0;
		endNumber = 0;
	}

	@Override
	public String createTextData(DataSchema dataSchema) {

		result = "0";
		if( dataSchema == null ) return result;

		String format = dataSchema.getOption().get(0);
		if( startNumber == 0 ) startNumber = Integer.parseInt(dataSchema.getOption().get(1));
		if( endNumber == 0 ) endNumber = Integer.parseInt(dataSchema.getOption().get(2));

		if( format.equals(OPTION_FORMAT.random.toString())){
			result = StringUtils.leftPad( String.valueOf( (int)Math.floor(Math.random()*(endNumber - startNumber +1)) + startNumber )
			, dataSchema.getSize(), '0');
		}else{
			result = StringUtils.leftPad( String.valueOf(startNumber), dataSchema.getSize(), '0' ) ;
			startNumber++;
			if( endNumber > 0 && startNumber > endNumber ){
				startNumber = 0;
			}
		}
		return result;
	}

}
