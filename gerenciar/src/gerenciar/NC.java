package gerenciar;

public class NC {
	public static BancoDeDadosNC bdNC = new BancoDeDadosNC();
	public void cadastrar() {
		
	}
	
	public void alterar(int e, int id) {
		if(e==1) {
			System.out.println("Digite a nova prioridade");
			do {
				e=MenuInicial.in.nextInt();
				if(e<0 || e>5) {
					System.out.println("Valor incorreto, digite um numero de 1 a 5");
				}
			}while(e<0 || e>5);
			bdNC.alterarNC(id, e);
		}
	}
	
	public void deletar() {
		
	}
	
	public void visualizar(int id) {
		int escolha=0;
		do {
			System.out.println("Escolha a opção: 1-Alterar prioridade NC\n2-Adicionar Resolução\n3-Inserir Usuário Responsável NC\n4-Inserir Equipe responsavel NC\n0-Sair");
			System.out.println("--------------------------------------------------------------------------------");
			bdNC.selecionarNC(String.valueOf(id));
			escolha = MenuInicial.in.nextInt();
			switch(escolha) {
			case 1:
				alterar(1,id);
				break;
			case 2:
				alterar(2,id);
				break;
			}
		}while(escolha!=0);
	}
	
	public NC(){
	}
}
