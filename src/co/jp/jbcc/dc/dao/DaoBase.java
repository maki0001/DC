package co.jp.jbcc.dc.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import co.jp.jbcc.dc.annotation.Bind;
import co.jp.jbcc.dc.common.Constants;

public abstract class DaoBase {

	private static final Logger LOGGER = Logger.getLogger(DaoBase.class);
	protected Connection con;

	protected <T> T bind2Dto( String dbName, String query, Class<T> returnClass ){

		Field[] fields = returnClass.getDeclaredFields();
		T result = null;
		con = null;
		try{
			con = DriverManager.getConnection( Constants.DERBY_DRIVER + dbName );
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			result = returnClass.newInstance();
			boolean isExist = false;
			while( rs.next() ){
				ResultSetMetaData rsm = rs.getMetaData();
				int j = 0;
				while( j++ < rsm.getColumnCount() ){
					for( Field f : fields ){
						Annotation a = f.getAnnotation(Bind.class);
						if( a != null ){
							Object val = rs.getObject( ((Bind) a).name() );
							f.setAccessible(true);
							f.set(result, val);
						}
					}
				}
				isExist = true;
			}

			if( !isExist ) result = null;

			rs.close();
			stmt.close();
			con.close();

		} catch ( Exception e ) {
			LOGGER.warn("", e);
		}finally{
			if( con != null ){
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

		return result;
	}

	protected <T> List<T>  bind2Dtos( String dbName, String query, Class<T> returnClass ){

		Field[] fields = returnClass.getDeclaredFields();
		List<T> results = new ArrayList<T>();
		try{
			con = DriverManager.getConnection( Constants.DERBY_DRIVER + dbName );
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			while( rs.next() ){
				T t = returnClass.newInstance();
				ResultSetMetaData rsm = rs.getMetaData();
				int i = 0;
				while( i++ < rsm.getColumnCount() ){
					for( Field f : fields ){
						Annotation a = f.getAnnotation(Bind.class);
						if( a != null ){
							Object val = rs.getObject( ((Bind) a).name() );
							f.setAccessible(true);
							f.set(t, val);
						}
					}
				}
				results.add(t);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch ( Exception e ) {
			LOGGER.warn("", e);;
		}finally{
			if( con != null ){
				try {
					con.close();
				} catch (SQLException e) {}
			}
		}

		return results;
	}
}
