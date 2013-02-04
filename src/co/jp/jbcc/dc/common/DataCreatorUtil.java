package co.jp.jbcc.dc.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public class DataCreatorUtil {

	public static String calendar2String( Calendar calendar, String format ){

		if( calendar == null ) return "9999/99/99";
		if( StringUtils.isEmpty(format) ) format = "yyyy-MM-dd";

		return new SimpleDateFormat(format).format( calendar.getTime() );
	}

}
