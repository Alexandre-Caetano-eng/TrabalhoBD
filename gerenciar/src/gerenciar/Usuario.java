package gerenciar;

public class Usuario {
	public static BancoDeDadosUsuario bdU = new BancoDeDadosUsuario();
	public static DataCheck dc = new DataCheck();
	
	public void cadastrar() {
		boolean existe=false;
		String nome="", email="", data="", senha="";
		int cpf=0, pis=0, idc=0, ide=0;
		System.out.println("Menu de cadastro de Usuário");
		System.out.println("Digite o nome do Usuário");
		do {
			nome=MenuInicial.in.next() + MenuInicial.in.nextLine();
			if(nome.equals("")) {
				System.out.println("O nome do Usuário não pode ser vazio");
			}
		}while(nome.equals(""));
		System.out.println("Digite o email do usuário");
		do {
			email=MenuInicial.in.nextLine();
			if(email.equals("")) {
				System.out.println("O email do usuário não pode ser vazio");
			}
		}while(email.equals(""));
		System.out.println("Digite a data de nascimento do usuário no formato ano(4 digitos)-mes(2)-dia(2)");
		existe=false;
		do {
			data=MenuInicial.in.next();
			existe = dc.VerificaData(data, 1);
			if(existe==false) {
				System.out.println("Erro, digite a data de nascimento do usuário no formato ano(4 digitos)-mes(2)-dia(2)");
			}
		}while(existe==false);
		System.out.println("Digite o cpf do usuário");		
		do {
			cpf=MenuInicial.in.nextInt();
			if(cpf==0) {
				System.out.println("Digite um cpf válido, não pode ser 0;");
			}
		}while(cpf==0);
		System.out.println("Digite o PIS do usuário");
		do {
			pis=MenuInicial.in.nextInt();
			if(pis==0) {
				System.out.println("Pis inválido, digite um valor diferente de 0.");
			}
		}while(pis==0);
		System.out.println("Digite a senha do Usuário");
		do {
			senha=MenuInicial.in.next();
			if(nome.equals("")) {
				System.out.println("A Senha do Usuário não pode ser vazio");
			}
		}while(nome.equals(""));
		System.out.println("Digite o id do Cargo do funcionário");
		do {
			idc=MenuInicial.in.nextInt();
			if(idc==0) {
				System.out.println("O Id do Cargo do Usuário não pode ser 0");
			}
		}while(idc==0);
		System.out.println("Digite o id da Equipe do funcionário");
		do {
			ide=MenuInicial.in.nextInt();
			if(ide==0) {
				System.out.println("O Id da Equipe do Usuário não pode ser 0");
			}
		}while(ide==0);
		bdU.cadastroUsuario(nome, email, data, cpf, pis, senha, idc, ide);
	}
	
	public void deletar() {
		int id=-1;
		do {
			System.out.println("Digite um id de usuário ou -1 para sair");
			id = MenuInicial.in.nextInt();
			if(id!=-1) {
				if(bdU.verificaUsuarioExiste(id)==true) {
					bdU.DeletarUsuario(id);
					id=-1;
				}else {
					System.out.println("Usuário não encontrado.");
				}
			}else {
				System.out.println("Saindo do deletar Usuário.");
			}
		}while(id!=-1);
	}
	
	public void visualizar() {
		int escolhau=0;
		do {
			bdU.visualizaTodosUsuarios();
			System.out.println("Escolha a opção: 1-Cadastrar Usuário	2-Deletar Usuário	0-Sair");
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
				System.out.println("Opção inválida, digite uma das opções.");
			}
		}while(escolhau!=0);
	}
	
	public Usuario(){
	}
}
