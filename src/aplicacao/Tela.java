package aplicacao;

import java.util.Scanner;

public class Tela {

	public static int menu(Scanner sc) {
		
		System.out.println("1 - UC1: Mostrar as corridas de um MotoTaxista por período");
		System.out.println("2 - UC2: Mostrar MotoTaxistas abaixo da média");
		System.out.println("3 - UC3: Mostrar MotoTaxistas acima da média");
		System.out.println("5 - UC4: Manter um novo MotoTaxista");
		System.out.println("6 - UC5: Mostrar a média de um MotoTaxista");
		System.out.println("7 - UC6: Manter uma nova Avaliação");
		System.out.println("8 - UC7: Manter um novo Chamado");
		System.out.println("9 - UC8: Manter uma nova Bandeirada");
		System.out.println("10 - UC9: Manter um novo Cliente");
		System.out.println("11 - UC10: Pesquisar MotoTaxistas");
		System.out.println("12 - UC11: Valor total de um chamado");
		System.out.println("13 - UC12: Pesquisar corridas por Cliente");
		System.out.println(" - Sair");
		return Integer.parseInt(sc.nextLine());
	
	}
}
