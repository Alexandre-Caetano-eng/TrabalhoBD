package gerenciar;

import java.util.Scanner;

public class MenuInicial {
	static Scanner in = new Scanner(System.in);
	public static int idu=0;
	public static String nome="";
	public static BancoDeDados bd = new BancoDeDados();
	public static MenuNC mnc = new MenuNC();
	
	
	public static void login() {
		String s;
		int u;
		System.out.println("Digite o id do Usu�rio");
		u = in.nextInt();
		System.out.println("Digite a senha do Usu�rio");
		s = in.next();
		bd.selecionarUsuario(u, s);
		if((idu!=0) & (!nome.equals(""))) {
			mnc.idu=idu;
			mnc.usuario=nome;
			mnc.visualizar();
		}
	}
	
	public static void esqueceuSenha() {
		int u;
		System.out.println("Digite o id do Usu�rio");
		u = in.nextInt();
		bd.selecionarUsuario(u);
	}

	public static void main(String[] args) {
		int escolha;
		bd.IniciaConexao();
		do {
			System.out.println("Escolha uma op��o");
			System.out.println("1 - Login");
			System.out.println("2 - Esqueceu Senha");
			System.out.println("0 - Sair");
			escolha= in.nextInt();
			switch(escolha) {
			case 1:
				login();
				break;
			case 2:
				esqueceuSenha();
				break;
			case 0:
				System.out.println("Tchau!");
				break;
			default:
				System.out.println("Op��o inv�lida");
			}
			in.reset();
		}while(escolha!=0);
		bd.EncerraConexao();
		in.close();
	}

}
