package gerenciar;

public class NC {
	public static BancoDeDadosNC bdNC = new BancoDeDadosNC();
	public static BancoDeDadosUsuario bdU = new BancoDeDadosUsuario();
	public static BancoDeDadosEquipe bdE = new BancoDeDadosEquipe();
	public void cadastrarResolucao() {
		
	}
	
	public void inserirUsuarioResp(int id) {
		int idu;
		if(bdNC.verificaNCUResp(id)!=false) {
			do {
				System.out.println("Digite uma id de válida Usuário ou -1 para sair");
				idu = MenuInicial.in.nextInt();
				if(bdU.verificaUsuarioExiste(idu)==true) {
					bdNC.alterarUsuarioRespNC(id, idu);
					idu=-1;
				}else {
					System.out.println("Id de usuário inválida.");
				}
			}while(idu!=-1);
		}else {
			System.out.println("NC já possui Usuário responsável");
		}
	}
	
	public void inserirEquipeResp(int id) {
		int ide;
		if(bdNC.verificaNCEResp(id)!=false) {
			do {
				System.out.println("Digite uma id de válida Usuário ou -1 para sair");
				ide = MenuInicial.in.nextInt();
				if(bdE.verificaEquipeExiste(ide)==true) {
					bdNC.alterarEquipeRespNC(id, ide);
					ide=-1;
				}else {
					System.out.println("Id de usuário inválida.");
				}
			}while(ide!=-1);
		}else {
			System.out.println("NC já possui uma Equipe responsável");
		}
	}
	
	public void alterarPrioridade(int id) {
		int e;
		System.out.println("Digite a nova prioridade");
		do {
			e=MenuInicial.in.nextInt();
			if(e<0 || e>5) {
				System.out.println("Valor incorreto, digite um numero de 1 a 5");
			}
		}while(e<0 || e>5);
		bdNC.alterarPrioridadeNC(id, e);
	}
	
	public void deletar() {
		
	}
	
	public void visualizar(int id) {
		int escolha=0;
		do {
			System.out.println("Escolha a opção: 1-Alterar prioridade NC\n2-Adicionar Resolução\n3-Inserir Usuário Responsável NC\n4-Inserir Equipe responsavel NC\n0-Sair");
			System.out.println("--------------------------------------------------------------------------------");
			bdNC.selecionarNCespecifica(id);
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Digite sua escolha: ");
			escolha = MenuInicial.in.nextInt();
			switch(escolha) {
			case 1:
				alterarPrioridade(id);
				break;
			case 2:
				break;
			case 3:
				inserirUsuarioResp(id);
				break;
			case 4:
				inserirEquipeResp(id);
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			}
		}while(escolha!=0);
	}
	
	public NC(){
	}
}
