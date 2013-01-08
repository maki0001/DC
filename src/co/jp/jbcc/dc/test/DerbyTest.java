package co.jp.jbcc.dc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

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

}
