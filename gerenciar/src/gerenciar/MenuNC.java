package gerenciar;

import java.sql.ResultSet;
import java.util.Scanner;

public class MenuNC {
	String usuario;
	int idu;
	public static ResultSet resultado;
	public static Scanner in = new Scanner("System.in");
	
	public void cadastrar() {
		
	}
	
	public void alterar() {
		
	}
	
	public void deletar() {
		
	}
	
	public void visualizar() {
		int escolha;
		do {
			System.out.print("1- MenuNC\t2-Escolher NC\t3-Audição");
			
			escolha = in.nextInt();
			switch(escolha) {
			case 1:
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
		
	}
	
	public MenuNC(String u, int id){
		usuario=u;
		idu=id;
	}
}
