package aplicacao;

import java.util.Scanner;

public class Tela {

	public static int menu(Scanner sc) {
		
		System.out.println("1 - UC1: Mostrar as corridas de um MotoTaxista por período");
		System.out.println("2 - UC2: Mostrar MotoTaxistas abaixo da média");
		System.out.println("3 - UC3: Mostrar MotoTaxistas acima da média");
		System.out.println("4 - UC4: Manter MotoTaxista");
		System.out.println("5 - UC5: Mostrar a média de um MotoTaxista");
		System.out.println("6 - UC6: Realizar Avaliação");
		System.out.println("7 - UC7: Solicitar Corrida");
		System.out.println("8 - UC8: Manter Bandeirada");
		System.out.println("9 - UC9: Cadastrar Cliente");
		System.out.println("10 - UC10: Pesquisar MotoTaxistas");
		System.out.println("11 - UC11: Pesquisar Chamado");
		System.out.println("12 - UC12: Pesquisar corridas por Cliente");
		System.out.println("13 - UC13: Manter Empresa");
		System.out.println("14 - Sair");
		return Integer.parseInt(sc.nextLine());
	
	}
}
