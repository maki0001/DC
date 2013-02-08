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
	
	@Test
	public void outputCategoryCsv1GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'category_1gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputItemCsv1GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "20000000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'item_1gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputStoreCsv1GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "200"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'store_1gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputPosItemCsv1GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "80000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem_1row_1gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	
	@Test
	public void outputCategoryCsv3GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'category_3gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputItemCsv3GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "50000000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'item_3gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputStoreCsv3GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "500"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'store_3gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputPosItemCsv3GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "4000000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem_3gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	
	@Test
	public void outputCategoryCsv5GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'category_5gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputItemCsv5GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "70000000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'item_5gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputStoreCsv5GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "700"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'store_5gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputPosItemCsv5GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "16170000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem_5gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	
	@Test
	public void outputCategoryCsv10GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "100000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'category_10gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputItemCsv10GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "100000000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'item_10gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputStoreCsv10GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "1000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'store_10gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
	@Test
	public void outputPosItemCsv10GB(){
		String[] args = new String[]{
				"DataCreateJob"
				, "62500000"
				, "comma"
				, "createTextData"
				, "select * from DATA_SCHEMA where FILE_NAME = 'posItem_10gb.csv'"
				, outputPath
				, "utf-8"
			};
		JobBase.main(args);
	}
}
