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
			System.out.println("Escolha a op��o: 1-Visualizar Usu�rio\t2-Cadastrar Usu�rio\t3-Alterar Usu�rio\t4-Deletar Usu�rio\t0-Sair");
			escolha = MenuInicial.in.nextInt();
		}while(escolha!=0);
	}
	
	public Usuario(String u){
		usuario=u;
	}
}
