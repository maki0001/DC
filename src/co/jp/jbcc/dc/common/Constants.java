package co.jp.jbcc.dc.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String CRLF = System.getProperty("line.separator");
	public static final Map<String, String> SEPARATOR_MAP = new HashMap<String, String>();
	public static final Map<String, String> CHAR_CODE_MAP = new HashMap<String, String>();
	static{
		SEPARATOR_MAP.put("tab", "\t");
		SEPARATOR_MAP.put("comma", ",");
		SEPARATOR_MAP.put("space", " ");

		CHAR_CODE_MAP.put("utf-8", "utf-8");
		CHAR_CODE_MAP.put("sjis", "sjis");
		CHAR_CODE_MAP.put(null, "utf-8");

	}


	public static final String DERBY_DRIVER = "jdbc:derby:";
	public static final String DB_DATA_CREATOR = "DATA_CREATOR";
}
