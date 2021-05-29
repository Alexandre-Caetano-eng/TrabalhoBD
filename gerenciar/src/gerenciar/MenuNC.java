package gerenciar;

import java.sql.ResultSet;


public class MenuNC {
	
	public static BancoDeDados bd = new BancoDeDados();
	
	String usuario;
	int idu;
	public static ResultSet resultado;
	
	public void cadastrar() {
		
	}
	
	public void alterar() {
		
	}
	
	public void deletar() {
		
	}
	
	public void visualizar() {
		int escolha;
		do {
			System.out.println("1- MenuNC\t2-Escolher NC\t3-Audição");
			bd.selecionarNC();
			escolha = MenuInicial.in.nextInt();
			switch(escolha) {
			case 1:
				System.out.println("11- Cadastrar NC\t12- Alterar NC\t13- Excluir\t99- Sair");
				escolha = MenuInicial.in.nextInt();
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("Numero digitado incorreto.");
				break;
			}
		}while(escolha!=99);
		MenuInicial.idu=0;
		MenuInicial.nome="";
	}
	
	public MenuNC(){
	}
}
