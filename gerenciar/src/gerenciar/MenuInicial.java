package gerenciar;

import java.util.Scanner;

public class MenuInicial {
	static Scanner in = new Scanner(System.in);
	public static int codu=0;
	public static String nome="";
	public static BancoDeDados bd = new BancoDeDados();
	public static BancoDeDadosUsuario bdu = new BancoDeDadosUsuario();
	
	
	public static void login() {
		String s;
		int u;
		System.out.println("Digite o id do Usuário");
		u = in.nextInt();
		System.out.println("Digite a senha do Usuário");
		s = in.next();
		bdu.loginUsuario(u, s);
	}
	
	public static void esqueceuSenha() {
		int u;
		System.out.println("Digite o id do Usuário");
		u = in.nextInt();
		bdu.senhaUsuario(u);
	}

	public static void main(String[] args) {
		int escolha;
		bd.IniciaConexao();
		do {
			System.out.println("Escolha uma opção");
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
				System.out.println("Opção inválida");
			}
			in.reset();
		}while(escolha!=0);
		bd.EncerraConexao();
		in.close();
	}

}
