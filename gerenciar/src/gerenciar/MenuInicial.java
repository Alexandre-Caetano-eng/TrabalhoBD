package gerenciar;

import java.util.Scanner;

public class MenuInicial {
	static Scanner in = new Scanner(System.in);
	static int[] usu = {1 ,2};
	static String[] senh = {"321","123"};
	public static BancoDeDados bd = new BancoDeDados();
	public static int idu;
	public static String nome;
	
	public static void login() {
		String s;
		int u;
		System.out.println("Digite o id do Usuário");
		u = in.nextInt();
		System.out.println("Digite a senha do Usuário");
		s = in.next();
		bd.selecionarUsuario(u, s);
	}
	
	public static void esqueceuSenha() {
		int u;
		System.out.println("Digite o id do Usuário");
		u = in.nextInt();
		bd.selecionarUsuario(u);
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
		}while(escolha!=0);
		bd.EncerraConexao();
	}

}
