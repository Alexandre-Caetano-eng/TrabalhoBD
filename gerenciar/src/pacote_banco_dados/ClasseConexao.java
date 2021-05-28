package pacote_banco_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClasseConexao {
	private static Connection conexao=null;
	//Método para fazer a conexão mysql
	public static Connection Conectar(String url, String user, String password) {
		try {
			//Verifica a conexão
			if(conexao==null) {
				conexao = DriverManager.getConnection(url,user,password);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}
	public static void FecharConexao(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
