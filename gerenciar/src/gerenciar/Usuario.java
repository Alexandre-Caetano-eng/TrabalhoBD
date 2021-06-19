package gerenciar;

public class Usuario {
	public static BancoDeDadosUsuario bdU = new BancoDeDadosUsuario();
	public static DataCheck dc = new DataCheck();
	
	public void cadastrar() {
		boolean existe=false;
		String nome="", email="", data="", senha="";
		int cpf=0, pis=0, idc=0, ide=0;
		System.out.println("Menu de cadastro de Usu�rio");
		System.out.println("Digite o nome do Usu�rio");
		do {
			nome=MenuInicial.in.next() + MenuInicial.in.nextLine();
			if(nome.equals("")) {
				System.out.println("O nome do Usu�rio n�o pode ser vazio");
			}
		}while(nome.equals(""));
		System.out.println("Digite o email do usu�rio");
		do {
			email=MenuInicial.in.nextLine();
			if(email.equals("")) {
				System.out.println("O email do usu�rio n�o pode ser vazio");
			}
		}while(email.equals(""));
		System.out.println("Digite a data de nascimento do usu�rio no formato ano(4 digitos)-mes(2)-dia(2)");
		existe=false;
		do {
			data=MenuInicial.in.next();
			existe = dc.VerificaData(data, 1);
			if(existe==false) {
				System.out.println("Erro, digite a data de nascimento do usu�rio no formato ano(4 digitos)-mes(2)-dia(2)");
			}
		}while(existe==false);
		System.out.println("Digite o cpf do usu�rio");		
		do {
			cpf=MenuInicial.in.nextInt();
			if(cpf==0) {
				System.out.println("Digite um cpf v�lido, n�o pode ser 0;");
			}
		}while(cpf==0);
		System.out.println("Digite o PIS do usu�rio");
		do {
			pis=MenuInicial.in.nextInt();
			if(pis==0) {
				System.out.println("Pis inv�lido, digite um valor diferente de 0.");
			}
		}while(pis==0);
		System.out.println("Digite a senha do Usu�rio");
		do {
			senha=MenuInicial.in.next();
			if(nome.equals("")) {
				System.out.println("A Senha do Usu�rio n�o pode ser vazio");
			}
		}while(nome.equals(""));
		System.out.println("Digite o id do Cargo do funcion�rio");
		do {
			idc=MenuInicial.in.nextInt();
			if(idc==0) {
				System.out.println("O Id do Cargo do Usu�rio n�o pode ser 0");
			}
		}while(idc==0);
		System.out.println("Digite o id da Equipe do funcion�rio");
		do {
			ide=MenuInicial.in.nextInt();
			if(ide==0) {
				System.out.println("O Id da Equipe do Usu�rio n�o pode ser 0");
			}
		}while(ide==0);
		bdU.cadastroUsuario(nome, email, data, cpf, pis, senha, idc, ide);
	}
	
	public void deletar() {
		int id=-1;
		do {
			System.out.println("Digite um id de usu�rio ou -1 para sair");
			id = MenuInicial.in.nextInt();
			if(id!=-1) {
				if(bdU.verificaUsuarioExiste(id)==true) {
					bdU.DeletarUsuario(id);
					id=-1;
				}else {
					System.out.println("Usu�rio n�o encontrado.");
				}
			}else {
				System.out.println("Saindo do deletar Usu�rio.");
			}
		}while(id!=-1);
	}
	
	public void visualizar() {
		int escolhau=0;
		do {
			bdU.visualizaTodosUsuarios();
			System.out.println("Escolha a op��o: 1-Cadastrar Usu�rio	2-Deletar Usu�rio	0-Sair");
			escolhau = MenuInicial.in.nextInt();
			switch(escolhau) {
			case 1:
				cadastrar();
				break;
			case 2:
				deletar();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Op��o inv�lida, digite uma das op��es.");
			}
		}while(escolhau!=0);
	}
	
	public Usuario(){
	}
}
