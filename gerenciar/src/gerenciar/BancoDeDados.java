package gerenciar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pacote_banco_dados.ClasseConexao;

public class BancoDeDados {
	static Connection conexao=null;
	
	public BancoDeDados(){
		
	}
	
	public void IniciaConexao() {
		String endereco="jdbc:mysql://localhost/gerenciamento_nc";
		String usuario="root";
		String senha="vertrigo";
		conexao = ClasseConexao.Conectar(endereco, usuario, senha);
	}
	
	public void selecionarUsuario(int i, String s) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(conexao!=null) {
				comando = conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i+" and senha="+s;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Login efetuado");
					MenuInicial.idu=resultado.getInt("idU");
					MenuInicial.nome=resultado.getString("nome");
				}else {
					System.out.println("Usuário ou Senha Inválidos");
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
			if(conexao!=null) {
				comando = conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Usuário encontrado, senha = "+resultado.getString("senha"));
				}else {
					System.out.println("Usuário não encontrado.");
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
	
	public void EncerraConexao() {
		ClasseConexao.FecharConexao(conexao);
	}
}
