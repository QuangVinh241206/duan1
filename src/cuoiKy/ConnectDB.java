package cuoiKy;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://LAPTOP-IJE704M9:1433; databaseName=QuanLyBanHangLinhKienMayTinh; encrypt=true; trustServerCertificate=true";
			String userName = "sa";
			String password = "241206";
			conn = DriverManager.getConnection(url, userName, password);
			if (conn != null)
				System.out.println("kết nối thành công!");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return conn;
	}
}
