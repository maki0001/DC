package co.jp.jbcc.dc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.arnx.jsonic.JSON;

import org.junit.Test;

import co.jp.jbcc.dc.data.schema.DataSchema;

public class JsonTest {

	@Test
	public void jsonEncodeTest(){

		DataSchema dataSchema = new DataSchema();
		dataSchema.setType("string");
		dataSchema.setSize(10);
//		dataSchema.setOption(new String[]{"alphabet"});

		System.out.println( JSON.encode(dataSchema));


	}


	@Test
	public void jsonDecodeTest(){

		String jsonStr = "[{\"type\":\"string\", \"size\":10, \"option\":\"alphabet\"},{\"type\":\"integer\", \"size\":10, \"option\":\"none\"}]";
		DataSchema[] dataSchemas = JSON.decode(jsonStr, DataSchema[].class);

		for( DataSchema dataSchema : dataSchemas ){
			System.out.print( dataSchema.getType() + "\t");
			System.out.print( dataSchema.getSize() + "\t");
//			System.out.print( dataSchema.getOption() + "\n");
		}

	}

	@Test
	public void db2JsonTest(){

		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:derby:DATA_CREATOR");

//			DatabaseMetaData dbMetaData = con.getMetaData();
//			ResultSet columnSchema = dbMetaData.getColumns(null, null, "DATA_SCHEMA", "");

			Statement stmt = con.createStatement();
			ResultSet row = stmt.executeQuery("select * from DATA_SCHEMA");
			while( row.next() ){
				ResultSetMetaData rsm = row.getMetaData();
				int i = 0;
				while( i++ < rsm.getColumnCount() ){
					System.out.println(row.getObject(i));
				}

				DataSchema[] dataSchemas = JSON.decode(row.getString("SCHEMA_TEXT"), DataSchema[].class);
				for( DataSchema dataSchema : dataSchemas ){
					System.out.print( dataSchema.getType() + "\t");
					System.out.print( dataSchema.getSize() + "\t");
//					System.out.print( dataSchema.getOption() + "\n");
				}
			}

			row.close();
			stmt.close();
			con.close();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if( con != null ){
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}
	}
}
