package co.jp.jbcc.dc.data.creator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.jp.jbcc.dc.common.DataCreatorUtil;
import co.jp.jbcc.dc.data.schema.DataSchema;

public class DateDataCreator extends CreatorBase {

	private Calendar startDateCalendar;
	private Calendar endDateCalendar;
	private Calendar todayCalendar;
	private String format;
	private String startDateString;
	private String endDateString;
	private DateFormat dateFormat;

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
	}

	@Override
	public String createTextData(DataSchema dataSchema) throws ParseException {

		result = "9999/99/99";
		if( dataSchema == null ) return result;

		//初回
		if( format == null ){
			format = dataSchema.getOption().get(0);
			startDateString =  dataSchema.getOption().get(1);
			endDateString = dataSchema.getOption().get(2);
			dateFormat = new SimpleDateFormat(format);
			startDateCalendar.setTime( dateFormat.parse(startDateString) );
			endDateCalendar.setTime( dateFormat.parse(endDateString) );
			result =  startDateString;
			return result;
		}

		startDateCalendar.add(Calendar.DATE, 1);
		if( startDateCalendar.compareTo(endDateCalendar) == 1 ){
			startDateCalendar.setTime( dateFormat.parse(startDateString) );
			result = startDateString;
			return result;
		}

		result = DataCreatorUtil.calendar2String(startDateCalendar, format);
		return result;
	}
}
