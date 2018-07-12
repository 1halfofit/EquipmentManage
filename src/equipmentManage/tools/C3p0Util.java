package equipmentManage.tools;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
* 描述：C3p0数据连接池工具
* @Date  2018年7月3日
* @version v1.0
*/
public class C3p0Util {
	private static DataSource ds =new ComboPooledDataSource();

		public C3p0Util() {
	}


		/**
		 * 从连接池中获取Connection
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
		 * 关闭连接
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