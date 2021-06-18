package gerenciar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosNC {

	public void selecionarNC(int a) {
		//seleciona todas a nc, 0 = n�o finalizadas, qualquer outra = finalizadas
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql;
				if(a==0) {
					sql ="SELECT * FROM NC WHERE dataTermino IS NULL";
				}else {
					sql ="SELECT * FROM NC WHERE dataTermino IS NOT NULL";
				}
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					System.out.print("C�digo NC: "+resultado.getInt("idNC")+" Nome: "+resultado.getString("nome")+" Prioridades: "+resultado.getInt("prioridades")+" ");
					if(resultado.getDate("dataTermino")==null) {
						System.out.println(" Em aberto data inicio "+resultado.getString("dataCriacao"));
					}else {
						System.out.println(" Encerrado na data "+resultado.getString("dataTermino"));
					}
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
	
	public void selecionarNCespecifica(int id) {
		//seleciona uma nc espec�fica
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT * FROM NC WHERE idNC="+id;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					System.out.println("C�digo NC: "+resultado.getInt("idNC")+" Nome: "+resultado.getString("nome")+"\t\t Prioridades: "+resultado.getInt("prioridades")+" ");
					System.out.println(" Em aberto data inicio "+resultado.getDate("dataCriacao")+" Encerrado "+resultado.getDate("dataTermino"));
					System.out.println("Descri��o: "+resultado.getString("descricao"));
					if(resultado.getString("resolucao")==null) {
						System.out.println("Resolu��o: Sem resolu��o ainda");
					}else {
						System.out.println("Resolu��o: "+resultado.getString("resolucao"));
					}
				}
				sql ="SELECT u.nome FROM NC AS n JOIN Usuario AS u ON n.idU=u.idU WHERE idNC="+id;
				comando.close();
				resultado.close();
				comando = null;
				resultado = null;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Nome do Usuario Respons�vel: "+resultado.getString("u.nome"));
				}else {
					System.out.println("Nome do Usuario Respons�vel: Sem usu�rio respons�vel");
				}
				comando.close();
				resultado.close();
				comando = null;
				resultado = null;
				sql ="SELECT e.nome FROM NC AS n JOIN Equipe AS e ON n.idE=e.idE WHERE idNC="+id;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					System.out.println("Nome da Equipe Respons�vel: "+resultado.getString("e.nome"));
				}else {
					System.out.println("Nome da Equipe Respons�vel: Sem equipe respons�vel");
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
	
	public String retornaResolucao(int id) {
		//seleciona uma nc espec�fica
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT * FROM NC WHERE idNC="+id;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					if(resultado.getString("resolucao")==null) {
						return "";
					}else {
						return resultado.getString("resolucao");
					}
					
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
	
	public boolean verificaNC(int id) {
		//verifica se uma nc existe
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT * FROM NC WHERE idNC="+id;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					return true;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
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
	
	public boolean verificaNCEResp(int id) {
		//verifica se uma nc existe
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT * FROM `nc` WHERE `idE` IS NULL and `idNC`="+id;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					return true;
				}else {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
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
	public boolean verificaNCUResp(int id) {
		//verifica se uma nc existe
		PreparedStatement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT `idU` FROM `nc` WHERE `idU` IS NULL and `idNC`="+id;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				if(resultado.next()) {
					return true;
				}else {
					return false;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
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
	
	public void cadastroNC(String n, String d, String data, int p, int u, int eq) {
		//cadastra uma nova nc
		PreparedStatement comando = null;
		ResultSet resultado = null;		
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="INSERT INTO `nc`(`nome`, `descricao`, `dataCriacao`, `prioridades`, `idU`, `idE`) VALUES (?,?,?,?,?,?)";
				comando = BancoDeDados.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				comando.setString(1,n);
				comando.setString(2,d);
				comando.setString(3, data);
				comando.setInt(4, p);
				if(u!=-1) {
					comando.setInt(5, u);
				}else {
					comando.setString(5, null);
				}
				if(eq!=-1) {
					comando.setInt(6, eq);
				}else {
					comando.setString(6, null);
				}
				if(comando.executeUpdate()>0) {
					resultado = comando.getGeneratedKeys();
					if(resultado.next()) {
						System.out.println("NC criada com o c�digo "+resultado.getInt(1));
					}
				}
				while(resultado.next()) {
					System.out.print("NC Cadastrada com sucesso");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc n�o cadastrada");
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
	
	public void adicionarResolucaoNC(int i, String r) {
		//altera a prioridade
		String resolucao = retornaResolucao(i);
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `nc` SET `resolucao`=? WHERE `idnc`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setString(1,resolucao+" "+r);
				comando.setInt(2,i);
				if(comando.executeUpdate()>0) {
					System.out.println("Resolu��o adicionada: "+r);
				}else {
					System.out.println("Resolu��o n�o foi adicionada");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc n�o cadastrada");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterarPrioridadeNC(int i,int p) {
		//altera a prioridade
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `nc` SET `prioridades`=? WHERE `idnc`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setInt(1,p);
				comando.setInt(2,i);
				if(comando.executeUpdate()>0) {
					System.out.println("Prioridade alterada para "+p);
				}else {
					System.out.println("Prioridade n�o foi alterada");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc n�o cadastrada");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterarEquipeRespNC(int i,int eq) {
		//altera a prioridade
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `nc` SET `idE`=? WHERE `idnc`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setInt(1,eq);
				comando.setInt(2,i);
				if(comando.executeUpdate()>0) {
					System.out.println("Nova equipe respons�vel.");
				}else {
					System.out.println("Equipe respons�vel n�o alterada.");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc n�o cadastrada");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterarUsuarioRespNC(int i,int u) {
		//altera a prioridade
		PreparedStatement comando = null;	
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="UPDATE `nc` SET `idU`=? WHERE `idnc`=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setInt(1,u);
				comando.setInt(2,i);
				if(comando.executeUpdate()>0) {
					System.out.println("Novo usu�rio respons�vel.");
				}else {
					System.out.println("Usu�rio respons�vel n�o alterado.");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc n�o cadastrada");
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deletarNC(int codnc) {
		PreparedStatement comando = null;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="DELETE FROM `NC` WHERE idNC=?";
				comando = BancoDeDados.conexao.prepareStatement(sql);
				comando.setInt(1, codnc);
				if(comando.executeUpdate()>0) {
					System.out.println("NC deletada");
				}else {
					System.out.println("NC n�o pode ser deletada, verifique o c�digo digitado");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				comando.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
