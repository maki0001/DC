package co.jp.jbcc.dc.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;

import co.jp.jbcc.dc.annotation.Bind;
import co.jp.jbcc.dc.dao.DataSchemaDao;
import co.jp.jbcc.dc.dto.DataSchemaDto;

public class DerbyTest {

	@Test
	public void simpleConnectTest(){

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
	public void bind2DtoTest(){

		DataSchemaDto dataSchemaDto = new DataSchemaDto();
		Field[] fields = dataSchemaDto.getClass().getDeclaredFields();
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
					for( Field f : fields ){
						Annotation a = f.getAnnotation(Bind.class);
						Object val = row.getObject( ((Bind) a).name() );
						f.setAccessible(true);
						f.set(dataSchemaDto, val);
					}
//					System.out.println(row.getObject(i));
				}
			}

			row.close();
			stmt.close();
			con.close();

		}catch(SQLException e){
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
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
	public void genericBind2DtoTest(){

		DataSchemaDao dataSchemaDao = new DataSchemaDao();

		DataSchemaDto dataSchemaDto = dataSchemaDao.bind2Dto("select * from DATA_SCHEMA order by ID asc fetch first 1 rows only");
		System.out.println(dataSchemaDto);

		List<DataSchemaDto> dataSchemaDtos = dataSchemaDao.bind2Dtos("select * from DATA_SCHEMA");
		for( DataSchemaDto dto : dataSchemaDtos ){
			System.out.println(dto);
		}
	}

}
