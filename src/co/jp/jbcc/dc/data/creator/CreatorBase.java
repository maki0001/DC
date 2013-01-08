package co.jp.jbcc.dc.data.creator;

import java.util.HashMap;
import java.util.Map;

import co.jp.jbcc.dc.data.schema.DataSchema;

public abstract class CreatorBase {

	public static final Map<String, Class> DATA_SCHEMA_MAP;
	static{
		DATA_SCHEMA_MAP = new HashMap<String, Class>();
		DATA_SCHEMA_MAP.put("string", StringDataCreator.class);
		DATA_SCHEMA_MAP.put("number", NumberDataCreator.class);
		DATA_SCHEMA_MAP.put("date", DateDataCreator.class);
	}

	protected String result;

	protected abstract String createTextData( DataSchema dataSchema );

}
