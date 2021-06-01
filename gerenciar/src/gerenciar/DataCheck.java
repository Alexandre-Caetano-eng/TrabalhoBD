package gerenciar;

public class DataCheck {

	public boolean VerificaData(String data, int tipo) {
		//trata a data apenas no formato ano[4]-mes[2]-dia[2]
		//tipo 1: ano[4]-mes[2]-dia[2]
		//tipo 2: mes[2]-dia[2]-ano[4]
		//tipo 3: dia[2]-mes[2]-ano[4]
		String[] array = data.split("");
		int posA=2,posB=5,posC,posD;
		if(tipo==1) {
			posA=4;
			posB=7;
		}
		for(int i=0;i<10;i++) {			
			if(i!=posA && i!=posB) {
				try {
					if(Integer.parseInt(array[i])<0 || Integer.parseInt(array[i])>9) {
						System.out.println("Formato da data inválido");
						return false;
					}
				}catch(Exception e) {
					if(array[i].equals("-")) {
						System.out.println("Tamanho de números na data inválido, digite o formato corretamente.");
					}else {
						System.out.println("Apenas números devem ser digitados nas data.");
					}
					return false;
				}
			}else {
				if(!array[i].equals("-")) {
					System.out.println("Simbolo que separa as datas inválidos");
					return false;
				}
			}
		}
		if(tipo==1) {
			posA=0;
			posB=1;
			posC=2;
			posD=3;
		}else {
			posA=6;
			posB=7;
			posC=8;
			posD=9;
		}
		String concatena=array[posA]+array[posB]+array[posC]+array[posD];
		int ano = Integer.parseInt(concatena);
		try {
			if(ano<2000) {
				System.out.println("Ano ínválido para cadastrar");
				return false;
			}
		}catch(Exception e) {
			System.out.println("Erro no ano digitado");
			return false;
		}
		if(tipo==1) {
			posA=5;
			posB=6;
			posC=8;
			posD=9;
		}else if(tipo==2){
			posA=0;
			posB=1;
			posC=3;
			posD=4;
		}else {
			posA=3;
			posB=4;
			posC=0;
			posD=1;
		}
		concatena= array[posA]+array[posB];
		int mes = Integer.parseInt(concatena);
		try {
			if(mes>12 || mes<1) {
				System.out.println("Mês ínválido para cadastrar, mês aceito de 01 a 12");
				return false;
			}
		}catch(Exception e) {
			System.out.println("Erro no mês digitado");
			return false;
		}
		concatena= array[posC]+array[posD];
		int dia = Integer.parseInt(concatena);
		try {
			if(dia>0) {
				if(mes==2) {
					if(ano%4==0) {
						if(dia>29) {
							System.out.println("Dia ínválido para cadastrar, dia aceito de 01 a 29");
							return false;
						}
					}else {
						if(dia>28) {
							System.out.println("Dia ínválido para cadastrar, dia aceito de 01 a 28");
							return false;
						}
					}	
				}else {
					if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12) {
						if(dia>31) {
							System.out.println("Dia ínválido para cadastrar, dia aceito de 01 a 31");
							return false;
						}
					}else {
						if(dia>30) {
							System.out.println("Dia ínválido para cadastrar, dia aceito de 01 a 30");
							return false;
						}
					}
				}
			}else {
				System.out.println("Dia menor que 1");
				return false;
			}
		}catch(Exception e) {
			System.out.println("Erro no dia digitado");
			return false;
		}
		return true;
	}
	
	public DataCheck() {
		
	}

}
