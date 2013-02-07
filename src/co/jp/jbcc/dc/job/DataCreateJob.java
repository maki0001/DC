package co.jp.jbcc.dc.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
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
	private int fileSize;
	private String separator;
	private String methodName;
	private String query;
	private String outputPath;
	private String charCode;
	private static String outputFileName;

	@Override
	protected void init( String[] args ) {
		recordCount = Integer.parseInt(args[0]);
		separator = Constants.SEPARATOR_MAP.get(args[1]);
		methodName = args[2];
		query = args[3];
		outputPath = args[4];
		charCode = Constants.CHAR_CODE_MAP.get(args[5]);
	}

	@Override
	public int run(String[] args) {

		LOGGER.info("********************* run ********************");

		if( args == null || args.length < 6 ){
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
		outputFileName = dataSchemaDto.getFileName();

		File[] outputFiles = new File(outputPath).listFiles( new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.equals( outputFileName );
			}
		});
		for( File f : outputFiles ){
			f.delete();
		}
		File outputFile = new File( outputPath + "/" + dataSchemaDto.getFileName() );

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

		OutputStream os = null;
		StringBuilder sb = new StringBuilder();
		try {

			os = new FileOutputStream(outputFile);

			//ヘッダー出力
			for( DataSchema dataSchema : dataSchemas ){
				sb.append(dataSchema.getHeader());
				if( ArrayUtils.indexOf(dataSchemas, dataSchema) == dataSchemas.length-1 ){
					sb.append(Constants.CRLF);
				}else{
					sb.append(separator);
				}
			}
			for( int i = 0; i < recordCount; i++){
				int j = 1;
				for( CreatorBase creatorBase : creatorMap.keySet() ){
					sb.append( creatorBase.getClass().getMethod(methodName, DataSchema.class).invoke( creatorBase, new Object[]{ creatorMap.get(creatorBase) }) );
					if( j < creatorMap.size() ) sb.append(separator);
					j++;
				}
				sb.append(Constants.CRLF);
				os.write( sb.toString().getBytes(charCode) );
				sb.setLength(0);
				System.out.println(i);
			}

		} catch ( Exception e ) {
			LOGGER.error("エラーが発生しました。", e);
		}finally{
			if( os != null ){
				try {
					os.flush();
					os.close();
				} catch (IOException e) {}
			}
		}

		System.out.println(sb.toString());
		return 0;
	}

	@Override
	protected void close() {

	}
}
