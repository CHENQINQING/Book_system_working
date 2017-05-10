package book_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCExecutor {
	//�������
	private static String DRIVER = PropertiesUtil.JDBC_DRIVER;
	//���url
	private static String URL = PropertiesUtil.JDBC_URL;
	//����������ݿ���û���
	private static String USER = PropertiesUtil.JDBC_USER;
	//����������ݿ������
	private static String PASS = PropertiesUtil.JDBC_PASS;
	//���Ӷ���
	private Connection connection;
	//ά��һ�������͵Ķ���
	private static JDBCExecutor jdbcExecutor;
	//Statement����,����ִ��SQL��䲢���ؽ��
	private Statement stmt;
	
	//˽�й�����
	private JDBCExecutor() throws ClassNotFoundException, SQLException {

			//��ʼ��JDBC���������������ص�jvm��
			Class.forName(DRIVER);
			//�������ݿ�����
			connection = DriverManager.getConnection(URL, USER, PASS);
			//����Statement����
			stmt = connection.createStatement();

	}
	
	//�ṩһ����̬�������ر����ʵ��
	public static JDBCExecutor getJDBCExecutor() throws ClassNotFoundException, SQLException {
		//���������ά��jdbcExecutor����Ϊ��,�����˽�еĹ��������ʵ��
		if (jdbcExecutor == null) {
			jdbcExecutor = new JDBCExecutor();
		}
		return jdbcExecutor;
	}
	
	/*
	 * ִ��һ���ѯ��sql
	 */
	public ResultSet executeQuery(String sql) throws SQLException {

			//����Statement����ִ�в�����sql
			ResultSet result = stmt.executeQuery(sql);
			return result;

	}
	
	//ִ�е���INSERT��UPDATE �� DELETE ���, ���ִ��INSERTʱ, ��������
	public int executeUpdate(String sql) throws SQLException {
		int result = -1;

			//ִ��SQL���
			stmt.executeUpdate(sql);
			//�������
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				//�������һ������
				result = rs.getInt(1);
			}
			rs.close();
			return result;

	}

}
