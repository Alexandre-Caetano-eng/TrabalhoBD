package gerenciar;

import java.sql.Connection;


import pacote_banco_dados.ClasseConexao;

public class BancoDeDados {
	public static Connection conexao=null;
	
	public BancoDeDados(){
		
	}
	
	public void IniciaConexao() {
		String endereco="jdbc:mysql://localhost/gerenciamento_nc";
		String usuario="root";
		String senha="vertrigo";
		conexao = ClasseConexao.Conectar(endereco, usuario, senha);
	}
	
	public void EncerraConexao() {
		ClasseConexao.FecharConexao(conexao);
	}
}
