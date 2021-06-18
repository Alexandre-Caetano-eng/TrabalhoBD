package gerenciar;

import java.util.InputMismatchException;

public class NC {
	public static BancoDeDadosNC bdNC = new BancoDeDadosNC();
	public static BancoDeDadosUsuario bdU = new BancoDeDadosUsuario();
	public static BancoDeDadosEquipe bdE = new BancoDeDadosEquipe();
	public void adicionarResolucao(int id) {
		String resolucao="", verifica;
		System.out.println("Digite uma resolução ou não digite nada para sair.");
		resolucao = MenuInicial.in.next() + MenuInicial.in.nextLine();
		verifica=resolucao.trim();
		if(verifica!="") {
			bdNC.adicionarResolucaoNC(id, resolucao);
		}else {
			System.out.println("Saindo da resolução.");
		}
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
				System.out.println("Digite uma id de equipe válida ou -1 para sair");
				ide = MenuInicial.in.nextInt();
				if(ide!=-1) {
					if(bdE.verificaEquipeExiste(ide)==true) {
						bdNC.alterarEquipeRespNC(id, ide);
						ide=-1;
					}else {
						System.out.println("Id de usuário inválida.");
					}
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
		String esc;
		do {
			System.out.println("--------------------------------------------------------------------------------");
			bdNC.selecionarNCespecifica(id);
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Escolha a opção: \n1-Alterar prioridade NC\n2-Adicionar Resolução\n3-Inserir Usuário Responsável NC\n4-Inserir Equipe responsavel NC\n0-Sair");
			System.out.println("Digite sua escolha: ");
			try {
				escolha = MenuInicial.in.nextInt();
			}catch(InputMismatchException e) {
				esc = MenuInicial.in.next();
				System.out.println(esc);
				escolha=-1;
			}
			switch(escolha) {
			case 1:
				alterarPrioridade(id);
				break;
			case 2:
				adicionarResolucao(id);
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
