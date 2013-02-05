package co.jp.jbcc.dc.data.creator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.jp.jbcc.dc.common.DataCreatorUtil;
import co.jp.jbcc.dc.data.schema.DataSchema;

public class DateDataCreator extends CreatorBase {

	private enum OPTION_FORMAT { cycle, group }

	private String optionFormatString;
	private Calendar startDateCalendar;
	private Calendar endDateCalendar;
	private Calendar todayCalendar;
	private String dateFormatString;
	private String startDateString;
	private String endDateString;
	private DateFormat dateFormat;

	private int groupIndex;
	private int groupRecordNumber;

	public DateDataCreator(){
		startDateCalendar = Calendar.getInstance();
		startDateCalendar.set(Calendar.HOUR, 0);
		startDateCalendar.set(Calendar.MINUTE, 0);
		startDateCalendar.set(Calendar.SECOND, 0);
		startDateCalendar.set(Calendar.MILLISECOND, 0);

		endDateCalendar = Calendar.getInstance();
		endDateCalendar.set(Calendar.HOUR, 0);
		endDateCalendar.set(Calendar.MINUTE, 0);
		endDateCalendar.set(Calendar.SECOND, 0);
		endDateCalendar.set(Calendar.MILLISECOND, 0);

		todayCalendar = Calendar.getInstance();
		todayCalendar.set(Calendar.HOUR, 0);
		todayCalendar.set(Calendar.MINUTE, 0);
		todayCalendar.set(Calendar.SECOND, 0);
		todayCalendar.set(Calendar.MILLISECOND, 0);

		groupIndex = 0;
		groupRecordNumber = 0;
	}

	@Override
	public String createTextData(DataSchema dataSchema) throws ParseException {

		result = "9999/99/99";
		if( dataSchema == null ) return result;

		//初回
		if( dateFormatString == null ){
			optionFormatString = dataSchema.getOption().get(0);
			dateFormatString = dataSchema.getOption().get(1);
			startDateString =  dataSchema.getOption().get(2);
			endDateString = dataSchema.getOption().get(3);
			dateFormat = new SimpleDateFormat(dateFormatString);
			startDateCalendar.setTime( dateFormat.parse(startDateString) );
			endDateCalendar.setTime( dateFormat.parse(endDateString) );
			result =  startDateString;
			groupIndex++;
			return result;
		}

		if( optionFormatString.equals( OPTION_FORMAT.cycle.toString() )){
			startDateCalendar.add(Calendar.DATE, 1);
		}else{
			if( groupRecordNumber == 0 ) groupRecordNumber = Integer.parseInt( dataSchema.getOption().get(4) );
			if( groupIndex++ == groupRecordNumber ){
				startDateCalendar.add(Calendar.DATE, 1);
				groupIndex = 0;
			}
		}

		if( startDateCalendar.compareTo(endDateCalendar) == 1 ){
			startDateCalendar.setTime( dateFormat.parse(startDateString) );
			result = startDateString;
			return result;
		}

		result = DataCreatorUtil.calendar2String(startDateCalendar, dateFormatString);
		return result;
	}
}
