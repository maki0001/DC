package co.jp.jbcc.dc.test;

import org.junit.Test;

import co.jp.jbcc.dc.job.JobBase;

public class JobTest {

	@Test
	public void outputCategoryCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "20000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'category.csv'"
				, "C:\\Users\\J32200\\workspace\\data\\DataCreator\\work"
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputItemCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "10000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'item.csv'"
				, "C:\\Users\\J32200\\workspace\\data\\DataCreator\\work"
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputStoreCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "10000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'store.csv'"
				, "C:\\Users\\J32200\\workspace\\data\\DataCreator\\work"
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputPosItemCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "10"
				, "tab"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem.csv'"
				, "C:\\Users\\J32200\\workspace\\data\\DataCreator\\work"
				, "utf-8"
			};

		JobBase.main(args);
	}
}
