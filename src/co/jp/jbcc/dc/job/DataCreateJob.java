package co.jp.jbcc.dc.job;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import co.jp.jbcc.dc.common.Constants;
import co.jp.jbcc.dc.dao.DataSchemaDao;
import co.jp.jbcc.dc.data.creator.CreatorBase;
import co.jp.jbcc.dc.data.schema.DataSchema;
import co.jp.jbcc.dc.dto.DataSchemaDto;


public class DataCreateJob extends JobBase{

	private static final Logger LOGGER = Logger.getLogger(DataCreateJob.class);

	private int recordCount;
	private String separator;
	private String methodName;
	private String query;

	@Override
	protected void init( String[] args ) {
		recordCount = Integer.parseInt(args[0]);
		separator = Constants.SEPARATOR_MAP.get(args[1]);
		methodName = args[2];
		query = args[3];
	}

	@Override
	public int run(String[] args) {

		LOGGER.info("********************* run ********************");

		if( args == null || args.length < 3 ){
			LOGGER.warn("引数が正しくありません。");
			return -1;
		}

		init( args );

		DataSchemaDao dataSchemaDao = new DataSchemaDao();
		DataSchemaDto dataSchemaDto = dataSchemaDao.bind2Dto( query );
		if( dataSchemaDto == null ){
			LOGGER.error("検索結果がありません。");
			return -1;
		}

		//追加順を保持するためにLinkedHashMapを使用
		DataSchema[] dataSchemas = DataSchema.getDataSchema(dataSchemaDto.getSchemaText());
		Map<CreatorBase, DataSchema> creatorMap = new LinkedHashMap<CreatorBase, DataSchema>();
		for( DataSchema dataSchema : dataSchemas){

			try {
				creatorMap.put(
						(CreatorBase) Class.forName( CreatorBase.DATA_SCHEMA_MAP.get(dataSchema.getType()).getName() ).newInstance()
						,dataSchema
					);
			} catch ( Exception e ) {
				LOGGER.error("エラーが発生しました。", e);
			}
		}

		StringBuilder sb = new StringBuilder();
		try {
			//ヘッダー出力
			for( DataSchema dataSchema : dataSchemas ){
				sb.append(dataSchema.getHeader()).append(separator);
				if( ArrayUtils.indexOf(dataSchemas, dataSchema) == dataSchemas.length-1 ){
					sb.append(Constants.CRLF);
				}
			}
			for( int i = 0; i < recordCount; i++){
				for( CreatorBase creatorBase : creatorMap.keySet() ){
					sb.append( creatorBase.getClass().getMethod(methodName, DataSchema.class).invoke( creatorBase, new Object[]{ creatorMap.get(creatorBase) }) )
						.append(separator);
				}
				sb.append(Constants.CRLF);
			}

		} catch ( Exception e ) {
			LOGGER.error("エラーが発生しました。", e);
		}

		System.out.println(sb.toString());
		return 0;
	}

	@Override
	protected void close() {

	}
}
