package aplicacao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dominio.Bandeirada;
import dominio.Cliente;
import dominio.Empresa;
import dominio.MotoTaxista;
import dominio.Chamado;
import servico.AvaliacaoServico;
import servico.BandeiradaServico;
import servico.ChamadoServico;
import servico.ClienteServico;
import servico.EmpresaServico;
import servico.MotoTaxistaServico;
import servico.ServicoException;
import servico.ServicoFactory;
import servico.impl.EmpresaServicoImpl;

@SuppressWarnings("unused")
public class Principal {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		int cod, op, opcao;
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
				System.out.println("\nEntre com o c�digo da Empresa: ");
				cod = Integer.parseInt(sc.nextLine());

				Empresa emp = empresaServico.buscar(cod);

				if (emp == null) {
					System.out.println("C�digo Inexistente!");
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
				break;
			case 2:
				double media = 0.0;
				System.out.println("\nEntre com o valor da m�dia : ");
				media = Double.parseDouble(sc.nextLine());
				
				Empresa emp1 = new Empresa();
				
				if(emp1.mototaxistasComMediaAbaixoDe(media) != null){
					List<MotoTaxista> mot2 = motoTaxistaServico.buscarTodos();
					System.out.println(mot2);
				}else{
					System.out.println("N�o h� nenhum mototaxista abaixo da m�dia");
				}
				
				break;
			case 3:
				System.out.println("Caso de uso ainda n�o implementado - Diego");
				break;
			case 4:
				System.out.println("Caso de uso ainda n�o implementado - Ana");
				break;
			case 5:
				System.out.println("\nDigite o c�digo do Moto-Taxista: ");
				cod = Integer.parseInt(sc.nextLine());

				MotoTaxista mot2 = motoTaxistaServico.buscar(cod);

				if (mot2 == null) {
					System.out.println("C�digo Inexistente!");
				} else {
					System.out.println("M�dia do mototaxista " + mot2.getNome() + ": " + mot2.media());
				}
				break;
			case 6:
				System.out.println("Caso de uso ainda n�o implementado - Ana");
				break;
			case 7:
				String endOrigem, endDestino;
				Double distancia;
				int cliente,mototaxista, codigo1;
				Bandeirada b;
				do{
					System.out.println("\nEscolha uma das op��es do menu abaixo:");
					System.out.println("1 - Inserir");
					System.out.println("2 - Alterar");
					System.out.println("3 - Pesquisar");
					System.out.println("4 - Excluir");
					System.out.println("5 - Sair");
					opcao = Integer.parseInt(sc.nextLine());

					switch (opcao) {
					case 1:	
						System.out.println("Digite o endere�o de origem: ");
						endOrigem = sc.nextLine();
						System.out.println("Digite o endere�o de destino: ");
						endDestino = sc.nextLine();
						System.out.println("Informe a dist�ncia aproximada em KM: ");
						distancia = Double.parseDouble(sc.nextLine());
						System.out.println("Informe seu c�digo de cliente: ");
						cliente = Integer.parseInt(sc.nextLine());
						
						Cliente cli = clienteServico.buscar(cliente);
						Date agora = new java.util.Date(); 
						
						if(cli == null){
							System.out.println("Erro: C�digo do cliente n�o encontrado. Forne�a um c�digo v�lido!!!");
						}else{
							System.out.println("Informe o c�digo do mototaxista: ");
							mototaxista= Integer.parseInt(sc.nextLine());
							
							MotoTaxista mot = motoTaxistaServico.buscar(mototaxista);
							if(mot == null){
								System.out.println("Erro: C�digo do mototaxista n�o encontrado. Forne�a um c�digo v�lido!!!");
							}else{ 
								if(agora.getHours() > 18){
									b = bandeiradaServico.buscar(1);
								}
								else{
									b = bandeiradaServico.buscar(2);
								}
								
								Chamado cha = new Chamado(null,endOrigem,endDestino,agora,distancia,cli,mot,b);
								try {
									chamadoServico.inserirChamado(cha);
									System.out.println("Chamado criado com sucesso!!!");
								} catch (ServicoException e) {
									System.out.println("Erro: Chamado n�o inserido. Verificar erro!!");
								}
							}
						}
						break;
					case 2:		
						System.out.println("Digite o c�digo do chamado: ");
						codigo1 = Integer.parseInt(sc.nextLine());
						
						Chamado c = chamadoServico.buscar(codigo1);
						
						if(c == null){
							
						}else{
							System.out.println("Digite o endere�o de origem: ");
							endOrigem = sc.nextLine();
							System.out.println("Digite o endere�o de destino: ");
							endDestino = sc.nextLine();
							System.out.println("Informe a dist�ncia aproximada em KM: ");
							distancia = Double.parseDouble(sc.nextLine());
							System.out.println("Informe seu c�digo de cliente: ");
							cliente = Integer.parseInt(sc.nextLine());
							
							Cliente cli1 = clienteServico.buscar(cliente);
							Date agora2 = new java.util.Date(); 
							
							if(cli1 == null){
								System.out.println("Erro: C�digo do cliente n�o encontrado. Forne�a um c�digo v�lido!!!");
							}else{
								System.out.println("Informe o c�digo do mototaxista: ");
								mototaxista= Integer.parseInt(sc.nextLine());
								
								MotoTaxista mot = motoTaxistaServico.buscar(mototaxista);
								if(mot == null){
									System.out.println("Erro: C�digo do mototaxista n�o encontrado. Forne�a um c�digo v�lido!!!");
								}else{ 
									if(agora2.getHours() > 18){
										b = bandeiradaServico.buscar(1);
									}
									else{
										b = bandeiradaServico.buscar(2);
									}
									
									Chamado cha = new Chamado(codigo1,endOrigem,endDestino,agora2,distancia,cli1,mot,b);
									try {
										chamadoServico.inserirChamado(cha);
										System.out.println("Chamado alterado com sucesso!!!");
									} catch (ServicoException e) {
										System.out.println("Erro: Chamado n�o alterado. Verificar erro!!");
									}
								}
							}
						}				
						break;
					case 3:
						System.out.println("Digite o c�digo do chamado a ser pesquisado:");
						codigo1 = Integer.parseInt(sc.nextLine());
						
						Chamado ch = chamadoServico.buscar(codigo1);
						
								if(ch == null){
									System.out.println("Erro: Chamado n�o encontrada. Digite outro valor v�lido!!!");
		
								}else{
									System.out.println("C�digo do chamado: " + ch.getCodChamado());
									System.out.println("Endere�o de Origem: " + ch.getOrigem());
									System.out.println("Endere�o de Destino: " + ch.getDestino());
									System.out.println("Hora do Chamado: " + ch.getDataHora());
									System.out.println("Dist�ncia Aproximada em KM: " + ch.getDistancia());
									System.out.println("Nome do Cliente: " + ch.getCliente().getNome());
									System.out.println("Nome do Mototaxista: " + ch.getMotoTaxista().getNome());
									System.out.println("Valor B�sico da Bandeirada: R$ " + ch.getBandeirada().getValorInicial());
							}
						break;
					case 4:
						System.out.println("Digite o c�digo do chamado:");
						codigo1 = Integer.parseInt(sc.nextLine());
						
						Chamado cha = chamadoServico.buscar(codigo1);
						
								if(cha == null){
									System.out.println("Erro: Chamado n�o encontrado. Digite outro valor v�lido!!!");
		
								}else{
									chamadoServico.excluir(cha);
									System.out.println("Chamado exclu�do com sucesso!!!");
							}
						break;
					case 5:
						System.out.println("**** ALTERA��ES NOS CHAMADOS FINALIZADAS COM SUCESSO!!!****\n");
						break;
						
					default:
						System.out.println("Op��o inv�lida!!!");
					}
				} while(opcao != 5);
				break;
			case 8:
				BigDecimal valor6, valor7;
				Double v6, v7;
				int codigo;
				do{
					System.out.println("\nEscolha uma das op��es do menu abaixo:");
					System.out.println("1 - Inserir");
					System.out.println("2 - Alterar");
					System.out.println("3 - Pesquisar");
					System.out.println("4 - Excluir");
					System.out.println("5 - Sair");
					opcao = Integer.parseInt(sc.nextLine());
					
					switch (opcao) {
					case 1:									
						System.out.println("Digite o valor inicial:");
						v6 = Double.parseDouble(sc.nextLine());
						System.out.println("Digite o valor por KM rodado:");
						v7 = Double.parseDouble(sc.nextLine());
						
						valor6 = new BigDecimal(v6);
						valor7 = new BigDecimal(v7);
						
						Bandeirada band = new Bandeirada(null, valor6, valor7);
						bandeiradaServico.inserirAtualizar(band);
						
						break;
					case 2:		
						System.out.println("Digite o c�digo da bandeirada:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Bandeirada bande = bandeiradaServico.buscar(codigo);
						
								if(bande == null){
									System.out.println("Erro: Bandeirada n�o encontrada. Encontre outro valor!!!");
		
								}else{
									System.out.println("Digite o novo valor inicial:");
									v6 = Double.parseDouble(sc.nextLine());
									System.out.println("Digite o novo valor por KM rodado:");
									v7 = Double.parseDouble(sc.nextLine());
									
									valor6 = new BigDecimal(v6);
									valor7 = new BigDecimal(v7);
									
									Bandeirada bandei = new Bandeirada(codigo, valor6, valor7);
									bandeiradaServico.inserirAtualizar(bandei);
									System.out.println("Bandeirada " + bandei.getCodBandeirada() + "alterado com sucesso!!!");
							}
						break;
					case 3:
						System.out.println("Digite o c�digo da bandeirada:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Bandeirada bandeir = bandeiradaServico.buscar(codigo);
						
								if(bandeir == null){
									System.out.println("Erro: Bandeirada n�o encontrada. Digite outro valor v�lido!!!");
		
								}else{
									bandeiradaServico.buscar(codigo);
									System.out.println("C�digo da bandeirada:" + bandeir.getCodBandeirada());
									System.out.println("Valor inicial:" + bandeir.getValorInicial());
									System.out.println("Valor do KM rodado:" + bandeir.getValorKM());
							}
						break;
					case 4:
						System.out.println("Digite o c�digo da bandeirada:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Bandeirada bandeira = bandeiradaServico.buscar(codigo);
						
								if(bandeira == null){
									System.out.println("Erro: Bandeirada n�o encontrada. Digite outro valor v�lido!!!");
		
								}else{
									bandeiradaServico.excluir(bandeira);
									System.out.println("Bandeirada exclu�da com sucesso!!!");
							}
						break;
					case 5:
						System.out.println("**** ALTERA��ES NAS BANDEIRADAS FINALIZADAS COM SUCESSO!!!****\n");
						break;
						
					default:
						System.out.println("Op��o inv�lida!!!");
					}
				} while(opcao != 5);
				break;
			case 9:
				System.out.println("Caso de uso ainda n�o implementado - Diego");
				break;
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
				System.out.println("Caso de uso ainda n�o implementado - Tiago");
				break;
			case 12:
				System.out.println("Caso de uso ainda n�o implementado - Tiago");
				break;
			case 13:
				System.out.println("Caso de uso ainda n�o implementado - Anna");
				break;
			case 14:
				System.out.println("Programa Encerrado.");
				break;
			}

		} while (op != 14);
		

	}

}
