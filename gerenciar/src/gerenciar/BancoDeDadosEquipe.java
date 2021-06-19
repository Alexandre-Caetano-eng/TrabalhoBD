package gerenciar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosEquipe {
	
	public void cadastroEquipe(String n) {
		//cadastra uma nova equipe
		PreparedStatement comando = null;
		ResultSet resultado = null;		
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `equipe`(`nome`) VALUES (?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,n);
				if(comando.executeUpdate()>0) {
					resultado = comando.getGeneratedKeys();
					if(resultado.next()) {
						System.out.println("Equipe criada com o código "+resultado.getInt(1));
					}
				}
				while(resultado.next()) {
					System.out.print("Equipe Cadastrada com sucesso");
				}
			}
		}catch(SQLException e) {
			System.out.print("Equipe não cadastrada");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void DeletarEquipe(int i) {
		//deleta equipe
		PreparedStatement comando = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="DELETE FROM `equipe` WHERE idE="+i;
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

				if(comando.executeUpdate()>0) {
					System.out.println("Equipe deletada com sucesso.");
				}else {
					System.out.println("Equipe não encontrada.");
				}
			}
		}catch(SQLException e) {
			System.out.print("Equipe não deletada");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void selecionaEquipes() {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM equipe";
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					System.out.println("Código Equipe: "+resultado.getInt("idE")+" Nome: "+resultado.getString("nome"));
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
	}
	
	public String nomeEquipes(int i) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM equipe WHERE idE="+i;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					return resultado.getString("nome");
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
		return "";
	}
	
	public boolean verificaEquipeExiste(int i) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM equipe WHERE idE="+i;
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
