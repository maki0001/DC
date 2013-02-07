package co.jp.jbcc.dc.data.creator;

import org.apache.commons.lang3.StringUtils;

import co.jp.jbcc.dc.data.schema.DataSchema;

/**
 *
 * @author J32200
 * number option:[ramdom or cycle or group or group, start number, end number, prifix word, group record number]
 */
public class NumberDataCreator extends CreatorBase {

	private enum OPTION_FORMAT { random, cycle ,group }
	private int startNumber;
	private int endNumber;
	private String prifixWord;

	private int groupIndex;
	private int groupRecordNumber;

	public NumberDataCreator(){
		startNumber = 0;
		endNumber = 0;
		groupIndex = 0;
		groupRecordNumber = 0;
	}

	@Override
	public String createTextData(DataSchema dataSchema) throws Exception{

		result = "0";
		if( dataSchema == null ) return result;

		String format = dataSchema.getOption().get(0);
		if( startNumber == 0 ) startNumber = Integer.parseInt(dataSchema.getOption().get(1));
		if( endNumber == 0 ) endNumber = Integer.parseInt(dataSchema.getOption().get(2));
		if( StringUtils.isEmpty(prifixWord) ) prifixWord = dataSchema.getOption().get(3);

		if( startNumber > endNumber ){
			throw new NumberFormatException("最小値が最大値を超えています。(" + dataSchema.getHeader() + ")");
		}

//		if( String.valueOf(endNumber).length() > dataSchema.getSize() ){
//			throw new NumberFormatException("最大値の桁数が指定桁数を超えています。(" + dataSchema.getHeader() + ")");
//		}

		if( format.equals(OPTION_FORMAT.random.toString()) ){
			result = StringUtils.leftPad( String.valueOf( (int)Math.floor(Math.random()*(endNumber - startNumber +1)) + startNumber )
			, dataSchema.getSize(), '0');
		}else if( format.equals(OPTION_FORMAT.cycle.toString()) ){
			result = StringUtils.leftPad( String.valueOf(startNumber), dataSchema.getSize(), '0' ) ;
			startNumber++;
		}else if( format.equals( OPTION_FORMAT.group.toString() )){
			if( groupRecordNumber == 0 ) groupRecordNumber = Integer.parseInt(dataSchema.getOption().get(4));
			result = StringUtils.leftPad( String.valueOf(startNumber), dataSchema.getSize(), '0');
			if( ++groupIndex == groupRecordNumber ){
				groupIndex = 0;
				startNumber++;
			}
		}

		if( startNumber > endNumber ){
			startNumber = 0;
		}

		if( ! StringUtils.isEmpty(prifixWord) ){
			return StringUtils.join( prifixWord, result );
		}

		return result;
	}

}
