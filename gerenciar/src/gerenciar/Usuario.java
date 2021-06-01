package gerenciar;

public class Usuario {
public String usuario;
	
	public void cadastrar() {
		
	}
	
	public void alterar() {
		
	}
	
	public void deletar() {
		
	}
	
	public void visualizar() {
		
	}
	
	public void MenuUsuario() {
		int escolha=0;
		do {
			System.out.println("Escolha a opção: 1-Visualizar Usuário\t2-Cadastrar Usuário\t3-Alterar Usuário\t4-Deletar Usuário\t0-Sair");
			escolha = MenuInicial.in.nextInt();
		}while(escolha!=0);
	}
	
	public Usuario(String u){
		usuario=u;
	}
}
