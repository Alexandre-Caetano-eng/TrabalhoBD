package gerenciar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosUsuario {

	public void loginUsuario(int i, String s) {
		//login de usu�rio
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i+" and senha="+s;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Login efetuado");
					new MenuNC(resultado.getInt("idU"), resultado.getString("nome"));
				}else {
					System.out.println("Usu�rio ou Senha Inv�lidos");
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
	
	public void selecionarUsuario(int i) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Usu�rio encontrado, senha = "+resultado.getString("senha"));
				}else {
					System.out.println("Usu�rio n�o encontrado.");
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
	
	public void selecionarUsuario(int i, boolean x) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					MenuNC.existe=true;
				}else {
					System.out.println("Usu�rio n�o encontrado.");
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
}
