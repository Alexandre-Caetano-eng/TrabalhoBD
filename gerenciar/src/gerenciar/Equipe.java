package gerenciar;

public class Equipe {
	public static BancoDeDadosEquipe bdE = new BancoDeDadosEquipe();
	
	public void cadastrarEquipe() {
		String nome="", verifica;
		System.out.println("Digite o nome da equipe ou deixe vazio para sair");
		nome = MenuInicial.in.next();
		verifica = nome.trim();
		if(verifica!="") {
			bdE.cadastroEquipe(nome);
		}else {
			System.out.println("Saindo do cadastro de Equipe.");
		}
	}
	
	public void deletarEquipe() {
		int id=-1;
		do {
			System.out.println("Digite o id da equipe ou digite -1 para sair");
			id = MenuInicial.in.nextInt();
			if(id!=-1) {
				if(bdE.verificaEquipeExiste(id)==true) {
					bdE.DeletarEquipe(id);
					id=-1;
				}else {
					System.out.println("Equipe não encontrada.");
				}
			}
			else{
				System.out.println("Saindo do cadastro de Equipe.");
			}
		}while(id!=-1);
	}
	
	public void visualizar() {
		int escolhaE=0;
		do {
			System.out.println("Menu das Equipes");
			System.out.println("---------------------------------------------------------------------------");
			bdE.selecionaEquipes();
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("Opções:	1-Cadastrar equipe		2-Deletar equipe	 0-Sair");
			escolhaE = MenuInicial.in.nextInt();
			switch(escolhaE) {
			case 1:
				cadastrarEquipe();
				break;
			case 2:
				deletarEquipe();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida.");
			}
		}while(escolhaE!=0);
	}
	
	public Equipe() {
		
	}
}
