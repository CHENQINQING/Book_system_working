package book_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCExecutor {
	//
	private static String DRIVER = PropertiesUtil.JDBC_DRIVER;
	//
	private static String URL = PropertiesUtil.JDBC_URL;
	//
	private static String USER = PropertiesUtil.JDBC_USER;
	//
	private static String PASS = PropertiesUtil.JDBC_PASS;
	//
	private Connection connection;
	//
	private static JDBCExecutor jdbcExecutor;
	//
	private Statement stmt;
	
	//
	private JDBCExecutor() throws ClassNotFoundException, SQLException {

			//
			Class.forName(DRIVER);
			//
			connection = DriverManager.getConnection(URL, USER, PASS);
			//
			stmt = connection.createStatement();

	}
	
	//
	public static JDBCExecutor getJDBCExecutor() throws ClassNotFoundException, SQLException {
		//
		if (jdbcExecutor == null) {
			jdbcExecutor = new JDBCExecutor();
		}
		return jdbcExecutor;
	}
	
	/*
	 * 
	 */
	public ResultSet executeQuery(String sql) throws SQLException {

			//
			ResultSet result = stmt.executeQuery(sql);
			return result;

	}
	
	//
	public int executeUpdate(String sql) throws SQLException {
		int result = -1;

			//
			stmt.executeUpdate(sql);
			//
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				//
				result = rs.getInt(1);
			}
			rs.close();
			return result;

	}

}
