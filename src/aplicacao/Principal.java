package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dominio.Empresa;
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
				System.out.println("\nEntre com o código da Empresa: ");
				cod = Integer.parseInt(sc.nextLine());

				Empresa emp = empresaServico.buscar(cod);

				if (emp == null) {
					System.out.println("Código Inexistente!");
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					System.out.println("\nInforme a Data Inicial: ");
					String d1 = sc.nextLine();
					Date dataInicial = sdf.parse(d1);

					System.out.println("\nInforme a Data Final: ");
					String d2 = sc.nextLine();
					Date dataFinal = sdf.parse(d2);

					System.out.println("Chamado(s) com data inicial de " + dataInicial + " e data final de " + dataFinal
							+ " da empresa " + emp.getNomeFantasia() + ": "
							+ emp.corridasPorPeriodo(dataInicial, dataFinal));
				}
			case 2:
				System.out.println("Caso de uso ainda não implementado - Roberto");
			case 3:
				System.out.println("Caso de uso ainda não implementado - Diego");
			case 4:
				System.out.println("Caso de uso ainda não implementado - Ana");
			case 5:
				System.out.println("\nDigite o código do Moto-Taxista: ");
				cod = Integer.parseInt(sc.nextLine());

				MotoTaxista mot2 = motoTaxistaServico.buscar(cod);

				if (mot2 == null) {
					System.out.println("Código Inexistente!");
				} else {
					System.out.println("Média do mototaxista " + mot2.getNome() + ": " + mot2.media());
				}
			case 6:
				System.out.println("Caso de uso ainda não implementado - Ana");
			case 7:
				System.out.println("Caso de uso ainda não implementado - Bruno");
			case 8:
				System.out.println("Caso de uso ainda não implementado - Bruno");
			case 9:
				System.out.println("Caso de uso ainda não implementado - Diego");
			case 10:
				System.out.println("UC10: Pesquisar MotoTaxistas");
				System.out.println("\nDigite o nome do moto taxista: ");
				String nome = sc.nextLine();

				if (nome.equals(""))
					nome = null;

				List<MotoTaxista> lista = motoTaxistaServico.buscarPorNome(nome);
				System.out.println("Resultado da Pesquisa");

				for (MotoTaxista x : lista) {
					System.out.println(x.getNome() + ", " + x.getPlaca() + ", " + x.getDisponivel() + ", "
							+ x.getEmpresa().getNomeFantasia() + "\n");
				}
				break;

			case 11:
				System.out.println("Caso de uso ainda não implementado - Tiago");
			case 12:
				System.out.println("Caso de uso ainda não implementado - Tiago");
			case 13:
				System.out.println("Caso de uso ainda não implementado - Anna");
			case 14:
				System.out.println("Programa Encerrado.");
				break;
			}

		} while (op != 14);

	}

}
