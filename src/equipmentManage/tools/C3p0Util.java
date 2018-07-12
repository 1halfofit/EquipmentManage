package equipmentManage.tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
* ������C3p0�������ӳع���
* @Date  2018��7��3��
* @version v1.0
*/
public class C3p0Util {
	private static DataSource ds =new ComboPooledDataSource();

		public C3p0Util() {
	}


		/**
		 * �����ӳ��л�ȡConnection
		 * @return Connection
		 */
		public static Connection getConn() {
			Connection connection = null;
			try {
				connection = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		
		/**
		 * �ر�����
		 * @param Conn
		 */
		public static void closeConn(Connection connection) {
			
			if(null != connection) {
				
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
}