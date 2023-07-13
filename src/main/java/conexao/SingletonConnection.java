package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {

	private static Connection connection = null;

	private static String url = "jdbc:mysql://localhost/lista_contatos";
	private static String user = "root";
	private static String pass = "root";

	static {
		obterConexao();
	}

	public SingletonConnection() {
		obterConexao();
	}

	public static void obterConexao() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, pass);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao se conectar", e);
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
}
