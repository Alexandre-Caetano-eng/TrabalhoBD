package gerenciar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosUsuario {

	public void loginUsuario(int i, String s) {
		//login de usuário
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i+" and senha="+s;
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Login efetuado.");
					new MenuNC(resultado.getInt("idU"), resultado.getString("nome"), resultado.getInt("idE"));
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
	
	public void senhaUsuario(int i) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
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
	
	public boolean verificaUsuarioExiste(int i) {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario WHERE idU="+i;
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
	
	public void visualizaTodosUsuarios() {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM usuario";
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print("Código Usuário: "+resultado.getInt("idU"));
					System.out.println(" Nome: "+resultado.getString("nome")+" Email: "+resultado.getString("email"));
					System.out.println("Data de Nascimento: "+resultado.getString("dataNascimento")+" CPF: "+resultado.getInt("CPF")+" PIS: "+resultado.getInt("PIS"));
					System.out.println("Senha: "+resultado.getString("senha")+" Id do Cargo: "+resultado.getString("idCargo")+" Id de Equipe: "+resultado.getString("IdE"));
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
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
	
	public void DeletarUsuario(int i) {
		//cdeleta usuário
		PreparedStatement comando = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="DELETE FROM `usuario` WHERE idU=?";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setInt(1,i);
				if(comando.executeUpdate()>0) {
					System.out.println("Usuário deletado com sucesso.");
				}else {
					System.out.println("Usuário não encontrado.");
				}
			}
		}catch(SQLException e) {
			System.out.print("Usuário não deletado");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void cadastroUsuario(String n, String e, String data, int cpf, int pis, String senha, int idc, int ideq) {
		//cadastra um usuario
		PreparedStatement comando = null;
		ResultSet resultado = null;		
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `usuario`(`nome`, `email`, `dataNascimento`, `CPF`, `PIS`, `senha`, `idCargo`, `idE`) VALUES (?,?,?,?,?,?,?,?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,n);
				comando.setString(2,e);
				comando.setString(3, data);
				comando.setInt(4, cpf);
				comando.setInt(5, pis);
				comando.setString(6,senha);
				comando.setInt(7,idc);
				comando.setInt(8,ideq);
				if(comando.executeUpdate()>0) {
					resultado = comando.getGeneratedKeys();
					if(resultado.next()) {
						System.out.println("Usuário criado com o código "+resultado.getInt(1)+".");
					}
				}
				while(resultado.next()) {
					System.out.print("Usuário Cadastrado com sucesso.");
				}
			}
		}catch(SQLException er) {
			System.out.print("Usuário não cadastrado.");
			er.printStackTrace();
		}finally{
			try {
				comando.close();
				resultado.close();
			}catch(SQLException es) {
				es.printStackTrace();
			}
		}
	}	
}
