package gerenciar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDadosNC {

	public void selecionarNC() {
		Statement comando = null;
		ResultSet resultado = null;
		try {
			if(BancoDeDados.conexao!=null) {
				comando = BancoDeDados.conexao.createStatement();
				String sql ="SELECT * FROM NC";
				resultado = comando.executeQuery(sql);
				while(resultado.next()) {
					System.out.print("Código NC: "+resultado.getInt("idNC")+" Nome: "+resultado.getString("nome")+" Prioridades: "+resultado.getInt("prioridades")+" ");
					if(resultado.getDate("dataTermino")==null) {
						System.out.print(" Em aberto\n");
					}else {
						System.out.print(" Encerrado\n");
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
	
	public void cadastroNC(String n, String d, String data, int p, int u, int eq) {
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
