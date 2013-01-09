package co.jp.jbcc.dc.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

public abstract class JobBase {

	private static final Logger LOGGER = Logger.getLogger(JobBase.class);

	private static final Map<String, Class> JOB_CLASS_MAP = new HashMap<String, Class>();
	static{
		JOB_CLASS_MAP.put("DataCreateJob", DataCreateJob.class);
	}
	private static final String EXECUTE_METHOD_NAME = "run";

	protected abstract void init(String[] args);
	protected abstract int run(String[] args);
	protected abstract void close();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("********************* Start job ********************");

		if( args == null || args.length < 2 ){
			LOGGER.warn("引数が正しくありません。");
			System.exit(1);
		}

		String[] params = ArrayUtils.subarray(args, 1, args.length);
		int exitCode = 0;

		try {

			exitCode = (Integer) JOB_CLASS_MAP.get(args[0]).getMethod(EXECUTE_METHOD_NAME, String[].class)
							.invoke(JOB_CLASS_MAP.get(args[0]).newInstance(), new Object[]{ params });

		} catch (Exception e) {
			LOGGER.error("ジョブ実行中にエラーが発生しました。", e);
		}

		System.exit(exitCode);
	}
}
