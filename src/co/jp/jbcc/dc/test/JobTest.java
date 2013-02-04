package co.jp.jbcc.dc.test;

import org.junit.Test;

import co.jp.jbcc.dc.job.JobBase;

public class JobTest {

	@Test
	public void simpleExecuteJobTest(){

		String[] args = new String[]{
				"DataCreateJob"
				, "10"
				, "tab"
				, "createTextData"
				, "select * from DATA_SCHEMA where ID = 4"
			};

		JobBase.main(args);
	}

}
