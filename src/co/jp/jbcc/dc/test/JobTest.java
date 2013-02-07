package co.jp.jbcc.dc.test;

import org.junit.Test;

import co.jp.jbcc.dc.job.JobBase;

public class JobTest {
	
	private String outputPath = "/home/jbcc/dev/workspace/data/DataCreator/work";

	@Test
	public void outputCategoryCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'category.csv'"
				, outputPath
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputItemCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "10000000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'item.csv'"
				, outputPath
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputStoreCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "100"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'store.csv'"
				, outputPath
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
				, outputPath
				, "utf-8"
			};

		JobBase.main(args);
	}

	@Test
	public void outputPosItem1rowCsvTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem_1row.csv'"
				, outputPath
				, "utf-8"
			};

		JobBase.main(args);
	}
}
