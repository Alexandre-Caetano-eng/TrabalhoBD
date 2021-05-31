package gerenciar;

import java.sql.ResultSet;


public class MenuNC {
	
	public static BancoDeDadosNC bdNC = new BancoDeDadosNC();
	public static BancoDeDadosUsuario bdU = new BancoDeDadosUsuario();
	public static DataCheck dc = new DataCheck();
	public static NC nc = new NC();
	
	String usuario;
	int codu;
	public static ResultSet resultado;
	public static boolean existe;
	
	public void cadastrar() {
		existe=false;
		String nome="", desc="", data="";
		int prio=0, uNC=-1, eNC=-1;
		System.out.println("Menu de cadastro de NC");
		System.out.println("Digite o nome da NC");
		do {
			nome=MenuInicial.in.next() + MenuInicial.in.nextLine();
			if(nome.equals("")) {
				System.out.println("O nome da NC não pode ser nulo");
			}
		}while(nome.equals(""));
		System.out.println("Digite a descrição da NC");
		do {
			desc=MenuInicial.in.nextLine();
			if(desc.equals("")) {
				System.out.println("A descrição da NC não pode ser nula");
			}
		}while(desc.equals(""));
		System.out.println("Digite a data da NC no formato ano(4 digitos)-mes(2)-dia(2)");
		existe=false;
		do {
			data=MenuInicial.in.next();
			existe = dc.VerificaData(data, 1);
			if(existe==false) {
				System.out.println("Erro, digite a data da NC no formato ano(4 digitos)-mes(2)-dia(2)");
			}
		}while(existe==false);
		System.out.println("Digite a prioridade da NC (0 < prioridade < 6)");		
		do {
			prio=MenuInicial.in.nextInt();
			if(prio>5 || prio<1) {
				System.out.println("Prioridade da NC incorreta, digite 1, 2, 3, 4 ou 5 para prioridade");
			}
		}while(prio>5 || prio<1);
		System.out.println("Digite o Usuario da NC (opcional, digite -1 se não tiver)");
		do {
			uNC=MenuInicial.in.nextInt();
			if(uNC!=-1) {
				bdU.selecionarUsuario(uNC, existe);
			}else {
				existe=true;
			}
		}while(existe==false);
		System.out.println("Digite a Equipe da NC (opcional, digite -1 se não tiver)");
		eNC=MenuInicial.in.nextInt();
		bdNC.cadastroNC(nome, desc, data, prio, uNC, eNC);
	}
	
	public void deletar() {
		int codnc=-1;
		System.out.println("Menu de deletar NC");
		do{
			System.out.println("Digite o codigo da NC a deletar (-1 para desistir)");
			codnc=MenuInicial.in.nextInt();
			bdNC.deletarNC(codnc);
			codnc=-1;
		}while(codnc!=-1);
	}
	
	public void Audicao() {
		System.out.println("Audição de NC");
		bdNC.selecionarNC(1);
	}
	
	public void visualizar() {
		int escolha;
		do {
			System.out.println("1- MenuNC\t2-Escolher NC");
			bdNC.selecionarNC(0);
			escolha = MenuInicial.in.nextInt();
			switch(escolha) {
			case 1:
				System.out.println("11- Cadastrar NC\t12- Audição NC \t12- Excluir NC\t99- Sair");
				escolha = MenuInicial.in.nextInt();
				switch(escolha) {
				case 11:
					cadastrar();
					break;
				case 12:
					break;
				case 13:
					deletar();
					break;
				case 99:
					break;
				default:
					System.out.println("Numero digitado incorreto.");
					escolha=0;
					break;
				}
				break;
			case 2:
				System.out.println("Digite o numero de id da NC ou -1 para sair");
				existe=false;
				do {
					escolha=MenuInicial.in.nextInt();
					if(escolha==-1) {
						existe=true;
					}else {
						existe=bdNC.selecionarNC((float)escolha);
						if(existe==true) {
							System.out.println("Abrindo NC");
							nc.visualizar(escolha);
						}
					}
				}while(existe==false);
				escolha=0;
				break;
			default:
				System.out.println("Numero digitado incorreto.");
				escolha=0;
				break;
			}
		}while(escolha!=99);
		System.out.println("Encerrando Login");
		MenuInicial.codu=0;
		MenuInicial.nome="";
	}
	
	public MenuNC(){
	}
}
