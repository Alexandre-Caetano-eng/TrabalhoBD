package gerenciar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosEquipe {
	public boolean verificaEquipeExiste(int i) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idE="+i;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					return true;
				}else {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
