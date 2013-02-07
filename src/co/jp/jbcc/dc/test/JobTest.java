package co.jp.jbcc.dc.test;

import org.junit.Test;

import co.jp.jbcc.dc.job.JobBase;

public class JobTest {

	@Test
	public void outputCategoryCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
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
				, "50000000"
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
				, "500"
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
				, "100"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem.csv'"
				, "C:\\Users\\J32200\\workspace\\data\\DataCreator\\work"
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputPosItem1rowCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "1250000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem_1row.csv'"
				, "C:\\Users\\J32200\\workspace\\data\\DataCreator\\work"
				, "utf-8"
			};

		JobBase.main(args);
	}
}
