package gerenciar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosNC {

	public void selecionarNC(int a) {
		//seleciona todas a nc, 0 = não finalizadas, qualquer outra = finalizadas
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
					System.out.print("Código NC: "+resultado.getInt("idNC")+" Nome: "+resultado.getString("nome")+" Prioridades: "+resultado.getInt("prioridades")+" ");
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
	
	public void selecionarNC(String id) {
		//seleciona uma nc espesifica
		PreparedStatement comando = null;
		ResultSet resultado = null;
		int num = Integer.parseInt(id);
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT * FROM NC WHERE idNC="+num;
				comando = BancoDeDados.conexao.prepareStatement(sql);
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					System.out.println("Código NC: "+resultado.getInt("idNC")+" Nome: "+resultado.getString("nome")+"\t\t Prioridades: "+resultado.getInt("prioridades")+" ");
					System.out.println(" Em aberto data inicio "+resultado.getDate("dataCriacao")+" Encerrado "+resultado.getDate("dataTermino"));
					System.out.println("Descrição: "+resultado.getString("descricao"));
					System.out.println("Resolução: "+resultado.getString("resolucao"));
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
	
	public boolean selecionarNC(float id) {
		//verifica se uma nc existe
		PreparedStatement comando = null;
		ResultSet resultado = null;
		int a=(int) id;
		try {
			if(BancoDeDados.conexao!=null) {
				String sql ="SELECT * FROM NC WHERE idNC="+a;
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
						System.out.println("NC criada com o código "+resultado.getInt(1));
					}
				}
				while(resultado.next()) {
					System.out.print("NC Cadastrada com sucesso");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc não cadastrada");
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
	
	public void alterarNC(int i,int p) {
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
					System.out.println("Prioridade não foi alterada");
				}
			}
		}catch(SQLException e) {
			System.out.print("Nc não cadastrada");
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
					System.out.println("NC não pode ser deletada, verifique o código digitado");
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
