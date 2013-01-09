package co.jp.jbcc.dc.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;

import org.junit.Before;
import org.junit.Test;

import co.jp.jbcc.dc.data.creator.CreatorBase;
import co.jp.jbcc.dc.data.schema.DataSchema;

public class CreatorTest {

	private DataSchema[] dataSchemas;

	@Before
	public void init(){

		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:derby:DATA_CREATOR");

			Statement stmt = con.createStatement();
			ResultSet row = stmt.executeQuery("select * from DATA_SCHEMA where ID = 2");
			while( row.next() ){
				ResultSetMetaData rsm = row.getMetaData();
				int i = 0;
				while( i++ < rsm.getColumnCount() ){
					System.out.println(row.getObject(i));
				}

				dataSchemas = JSON.decode(row.getString("SCHEMA_TEXT"), DataSchema[].class);
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

	@Test
	public void textDataCreatorTest(){

		List<CreatorBase> creators = new ArrayList<CreatorBase>();
		//追加順を保持するためにLinkedHashMapを使用
		Map<CreatorBase, DataSchema> creatorMap = new LinkedHashMap<CreatorBase, DataSchema>();
		for( DataSchema dataSchema : dataSchemas){

			try {
				creatorMap.put(
						(CreatorBase) Class.forName( CreatorBase.DATA_SCHEMA_MAP.get(dataSchema.getType()).getName() ).newInstance()
						,dataSchema
					);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println(creators.size());
		}

		int recordCount = 10;
		String separator = "\t";
		String methodName = "createTextData";
		StringBuilder sb = new StringBuilder();

		try {
			for( int i = 0; i < recordCount; i++){
				for( CreatorBase creatorBase : creatorMap.keySet() ){
					sb.append( creatorBase.getClass().getMethod(methodName, DataSchema.class).invoke( creatorBase, new Object[]{ creatorMap.get(creatorBase) }) )
						.append(separator);
				}
				sb.append("\n");
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		System.out.println(sb.toString());
	}

}
