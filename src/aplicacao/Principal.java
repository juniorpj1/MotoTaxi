package aplicacao;

import java.text.ParseException;
import java.util.Scanner;

import dominio.MotoTaxista;
import servico.AvaliacaoServico;
import servico.BandeiradaServico;
import servico.ChamadoServico;
import servico.ClienteServico;
import servico.EmpresaServico;
import servico.MotoTaxistaServico;
import servico.ServicoFactory;

@SuppressWarnings("unused")
public class Principal {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		int cod, op;
		AvaliacaoServico avaliacaoServico = ServicoFactory.criarAvaliacaoServico();
		BandeiradaServico bandeiradaServico = ServicoFactory.criarBandeiradaServico();
		ChamadoServico chamadoServico = ServicoFactory.criarChamadoServico();
		ClienteServico clienteServico = ServicoFactory.criarClienteServico();
		EmpresaServico empresaServico = ServicoFactory.criarEmpresaServico();
		MotoTaxistaServico motoTaxistaServico = ServicoFactory.criarMotoTaxistaServico();

		do {
			op = Tela.menu(sc);

			switch (op) {
			case 1:
				System.out.println("Caso de uso ainda não implementado - Aparício");
			case 2:
				System.out.println("Caso de uso ainda não implementado - Roberto");
			case 3:
				System.out.println("Caso de uso ainda não implementado - Diego");
			case 4:
				System.out.println("Caso de uso ainda não implementado - Ana");
			case 5:
				System.out.println("Caso de uso ainda não implementado - Aparício");
			case 6:
				System.out.println("Caso de uso ainda não implementado - Ana");
			case 7:
				System.out.println("Caso de uso ainda não implementado - Bruno");
			case 8:
				System.out.println("Caso de uso ainda não implementado - Bruno");
			case 9:
				System.out.println("Caso de uso ainda não implementado - Diego");
			case 10:
				System.out.println("Caso de uso ainda não implementado - Roberto");
			case 11:
				System.out.println("Caso de uso ainda não implementado - Tiago");
			case 12:
				System.out.println("Caso de uso ainda não implementado - Tiago");
			}

		} while (op != 10);

	}

}
