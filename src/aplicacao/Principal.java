package aplicacao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dominio.Bandeirada;
import dominio.Chamado;
import dominio.Cliente;
import dominio.Empresa;
import dominio.MotoTaxista;
import servico.AvaliacaoServico;
import servico.BandeiradaServico;
import servico.ChamadoServico;
import servico.ClienteServico;
import servico.EmpresaServico;
import servico.MotoTaxistaServico;
import servico.ServicoException;
import servico.ServicoFactory;

@SuppressWarnings("unused")
public class Principal {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		int cod, op, op2;
		AvaliacaoServico avaliacaoServico = ServicoFactory.criarAvaliacaoServico();
		BandeiradaServico bandeiradaServico = ServicoFactory.criarBandeiradaServico();
		ChamadoServico chamadoServico = ServicoFactory.criarChamadoServico();
		ClienteServico clienteServico = ServicoFactory.criarClienteServico();
		EmpresaServico empresaServico = ServicoFactory.criarEmpresaServico();
		MotoTaxistaServico motoTaxistaServico = ServicoFactory.criarMotoTaxistaServico();

		do {
			op = Tela.menu(sc);

			int opcao;
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
				break;
			case 2:
				double media = 0.0;
				System.out.println("\nEntre com o valor da média : ");
				media = Double.parseDouble(sc.nextLine());
				
				Empresa emp1 = new Empresa();
				
				if(emp1.mototaxistasComMediaAbaixoDe(media) != null){
					List<MotoTaxista> mot2 = motoTaxistaServico.buscarTodos();
					System.out.println(mot2);
				}else{
					System.out.println("Não há nenhum mototaxista abaixo da média");
				}
				
				break;
			case 3:
				System.out.println("Caso de uso ainda não implementado - Diego");
				break;
			case 4:
				
				do{
					int codigo4, codigo;

					System.out.println("01 - Cadastrar MotoTaxista");
					System.out.println("02 - Alterar MotoTaxista");
					System.out.println("03 - Pesquisar MotoTaxista");
					System.out.println("04 - Excluir MotoTaxista");
					System.out.println("05 - Sair");

					op2 = Integer.parseInt(sc.nextLine());
					
					switch (op2) {
					case 1://Inserir MotoTaxista
					
						System.out.println("\nDigite o nome do MotoTaxista: ");
						String nome = sc.nextLine();
						System.out.println("\nDigite o número da placa do MotoTaxista: ");
						String placa = sc.nextLine();
						System.out.println("Digite o código da Empresa pelo qual o MotoTaxista trabalha: ");
						int codEmpresa = Integer.parseInt(sc.nextLine());
						
						Empresa empr1 = empresaServico.buscar(codEmpresa);
						
						if(empr1 == null){
							System.out.println("Não encontrado");
						}else{
							MotoTaxista novomotaxista1 = new MotoTaxista(null, nome, placa, true, empr1); 
							motoTaxistaServico.inserirAtualizar(novomotaxista1);
						}
						break;
				
					case 2: //Alterar MotoTaxista
						System.out.println("Digite o código do MotoTaxista: ");
						codigo4 = Integer.parseInt(sc.nextLine());
						
						MotoTaxista mototaxista2 = motoTaxistaServico.buscar(codigo4);
						
							if(mototaxista2 == null){
								System.out.println("Código inexistente! ");
							}else{
								System.out.println("Digite um novo nome: ");
								String novonome = sc.nextLine();
								System.out.println("Digite uma nova placa: ");
								String novaplaca = sc.nextLine();
								System.out.println("O MotoTaxista está disponível?");
								boolean novoestado = Boolean.parseBoolean(sc.nextLine());
								System.out.println("Digite o código da Empresa pelo qual o MotoTaxista trabalha: ");
								int novocodEmpresa = Integer.parseInt(sc.nextLine());
								
								Empresa empr2 = empresaServico.buscar(novocodEmpresa);
								
								
								if(empr2 == null){
									System.out.println("Não encontrado");
								}else{
									MotoTaxista novomotaxista2 = new MotoTaxista(codigo4, novonome, novaplaca, true, empr2); 
									motoTaxistaServico.inserirAtualizar(novomotaxista2);
									System.out.println("Dados alterados com sucesso!");
								}
							
								
							}
							
					case 3://Pesquisar
						System.out.println("Digite o código do MotoTaxista:");
						codigo = Integer.parseInt(sc.nextLine());
						
						MotoTaxista mototaxista3 = motoTaxistaServico.buscar(codigo);
						
								if(mototaxista3 == null){
									System.out.println("Erro: MotoTaxista não encontrado. Digite outro valor válido.");
		
								}else{
									bandeiradaServico.buscar(codigo);
									System.out.println("Código do MotoTaxista:" + mototaxista3.getCodMotoTaxista());
									System.out.println("Nome:" + mototaxista3.getNome());
									System.out.println("Placa:" + mototaxista3.getPlaca()+"\n");
							}
						break;
				
					case 4://Excluir
						System.out.println("Digite o código do MotoTaxista:");
						codigo = Integer.parseInt(sc.nextLine());
						
						MotoTaxista mototaxista4 = motoTaxistaServico.buscar(codigo);
						
								if(mototaxista4 == null){
									System.out.println("Erro: MotoTaxista não encontrado. Encontre outro valor.");
		
								}else{
									motoTaxistaServico.excluir(mototaxista4);
									System.out.println("MotoTaxista excluído com sucesso!");
							}
							
					case 5://Sair
						System.out.println("Alterações no MotoTaxista finalizadas.\n");
						break;
					
					default:
						System.out.println("Opção inválida!");
					}
				} while(op2 != 5);
				break;
			
			
			case 5:
				System.out.println("\nDigite o código do Moto-Taxista: ");
				cod = Integer.parseInt(sc.nextLine());

				MotoTaxista mot2 = motoTaxistaServico.buscar(cod);

				if (mot2 == null) {
					System.out.println("Código Inexistente!");
				} else {
					System.out.println("Média do mototaxista " + mot2.getNome() + ": " + mot2.media());
				}
				break;
				
			case 7:
				String endOrigem, endDestino;
				Double distancia;
				int cliente,mototaxista, codigo1;
				Bandeirada b;
				do{
					System.out.println("\nEscolha uma das opções do menu abaixo:");
					System.out.println("1 - Inserir");
					System.out.println("2 - Alterar");
					System.out.println("3 - Pesquisar");
					System.out.println("4 - Excluir");
					System.out.println("5 - Sair");
					opcao = Integer.parseInt(sc.nextLine());

					switch (opcao) {
					case 1:	
						System.out.println("Digite o endereço de origem: ");
						endOrigem = sc.nextLine();
						System.out.println("Digite o endereço de destino: ");
						endDestino = sc.nextLine();
						System.out.println("Informe a distância aproximada em KM: ");
						distancia = Double.parseDouble(sc.nextLine());
						System.out.println("Informe seu código de cliente: ");
						cliente = Integer.parseInt(sc.nextLine());
						
						Cliente cli = clienteServico.buscar(cliente);
						Date agora = new java.util.Date(); 
						
						if(cli == null){
							System.out.println("Erro: Código do cliente não encontrado. Forneça um código válido!!!");
						}else{
							System.out.println("Informe o código do mototaxista: ");
							mototaxista= Integer.parseInt(sc.nextLine());
							
							MotoTaxista mot = motoTaxistaServico.buscar(mototaxista);
							if(mot == null){
								System.out.println("Erro: Código do mototaxista não encontrado. Forneça um código válido!!!");
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
									System.out.println("Erro: Chamado não inserido. Verificar erro!!");
								}
							}
						}
						break;
					case 2:		
						System.out.println("Digite o código do chamado: ");
						codigo1 = Integer.parseInt(sc.nextLine());
						
						Chamado c = chamadoServico.buscar(codigo1);
						
						if(c == null){
							
						}else{
							System.out.println("Digite o endereço de origem: ");
							endOrigem = sc.nextLine();
							System.out.println("Digite o endereço de destino: ");
							endDestino = sc.nextLine();
							System.out.println("Informe a distância aproximada em KM: ");
							distancia = Double.parseDouble(sc.nextLine());
							System.out.println("Informe seu código de cliente: ");
							cliente = Integer.parseInt(sc.nextLine());
							
							Cliente cli1 = clienteServico.buscar(cliente);
							Date agora2 = new java.util.Date(); 
							
							if(cli1 == null){
								System.out.println("Erro: Código do cliente não encontrado. Forneça um código válido!!!");
							}else{
								System.out.println("Informe o código do mototaxista: ");
								mototaxista= Integer.parseInt(sc.nextLine());
								
								MotoTaxista mot = motoTaxistaServico.buscar(mototaxista);
								if(mot == null){
									System.out.println("Erro: Código do mototaxista não encontrado. Forneça um código válido!!!");
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
										System.out.println("Erro: Chamado não alterado. Verificar erro!!");
									}
								}
							}
						}				
						break;
					case 3:
						System.out.println("Digite o código do chamado a ser pesquisado:");
						codigo1 = Integer.parseInt(sc.nextLine());
						
						Chamado ch = chamadoServico.buscar(codigo1);
						
								if(ch == null){
									System.out.println("Erro: Chamado não encontrada. Digite outro valor válido!!!");
		
								}else{
									System.out.println("Código do chamado: " + ch.getCodChamado());
									System.out.println("Endereço de Origem: " + ch.getOrigem());
									System.out.println("Endereço de Destino: " + ch.getDestino());
									System.out.println("Hora do Chamado: " + ch.getDataHora());
									System.out.println("Distância Aproximada em KM: " + ch.getDistancia());
									System.out.println("Nome do Cliente: " + ch.getCliente().getNome());
									System.out.println("Nome do Mototaxista: " + ch.getMotoTaxista().getNome());
									System.out.println("Valor Básico da Bandeirada: R$ " + ch.getBandeirada().getValorInicial());
							}
						break;
					case 4:
						System.out.println("Digite o código do chamado:");
						codigo1 = Integer.parseInt(sc.nextLine());
						
						Chamado cha = chamadoServico.buscar(codigo1);
						
								if(cha == null){
									System.out.println("Erro: Chamado não encontrado. Digite outro valor válido!!!");
		
								}else{
									chamadoServico.excluir(cha);
									System.out.println("Chamado excluído com sucesso!!!");
							}
						break;
					case 5:
						System.out.println("**** ALTERAÇÕES NOS CHAMADOS FINALIZADAS COM SUCESSO!!!****\n");
						break;
						
					default:
						System.out.println("Opção inválida!!!");
					}
				} while(opcao != 5);
				break;
			case 8:
				BigDecimal valor6, valor7;
				Double v6, v7;
				int codigo;
				do{
					System.out.println("\nEscolha uma das opções do menu abaixo:");
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
						System.out.println("Digite o código da bandeirada:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Bandeirada bande = bandeiradaServico.buscar(codigo);
						
								if(bande == null){
									System.out.println("Erro: Bandeirada não encontrada. Encontre outro valor!!!");
		
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
						System.out.println("Digite o código da bandeirada:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Bandeirada bandeir = bandeiradaServico.buscar(codigo);
						
								if(bandeir == null){
									System.out.println("Erro: Bandeirada não encontrada. Digite outro valor válido!!!");
		
								}else{
									bandeiradaServico.buscar(codigo);
									System.out.println("Código da bandeirada:" + bandeir.getCodBandeirada());
									System.out.println("Valor inicial:" + bandeir.getValorInicial());
									System.out.println("Valor do KM rodado:" + bandeir.getValorKM());
							}
						break;
					case 4:
						System.out.println("Digite o código da bandeirada:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Bandeirada bandeira = bandeiradaServico.buscar(codigo);
						
								if(bandeira == null){
									System.out.println("Erro: Bandeirada não encontrada. Digite outro valor válido!!!");
		
								}else{
									bandeiradaServico.excluir(bandeira);
									System.out.println("Bandeirada excluída com sucesso!!!");
							}
						break;
					case 5:
						System.out.println("**** ALTERAÇÕES NAS BANDEIRADAS FINALIZADAS COM SUCESSO!!!****\n");
						break;
						
					default:
						System.out.println("Opção inválida!!!");
					}
				} while(opcao != 5);
				break;
			case 9:
				System.out.println("Caso de uso ainda não implementado - Diego");
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
				System.out.println("Caso de uso ainda não implementado - Tiago");
				break;
			case 12:
				System.out.println("Caso de uso ainda não implementado - Tiago");
				break;
				
				case 13:
				do{
					int cod1;

					System.out.println("01 - Cadastrar Empresa");
					System.out.println("02 - Alterar Empresa");
					System.out.println("03 - Pesquisar Empresa");
					System.out.println("04 - Excluir Empresa");
					System.out.println("05 - Sair");

					op2 = Integer.parseInt(sc.nextLine());
					
					switch (op2) {
					case 1://Inserir Empresa
					
						System.out.println("\nInforme o nome da Empresa: ");
						String nomefantasia = sc.nextLine();
						
						System.out.println("Razão social: ");
						String razaoSocial = sc.nextLine();
						
						System.out.println("Telefone: ");
						String telefone = sc.nextLine();
						
						System.out.println("E-mail: ");
						String email = sc.nextLine();
						
						System.out.println("CNPJ: ");
						String cnpj = sc.nextLine();
						
						System.out.println("Logradouro: ");
						String logradouro = sc.nextLine();
						
						System.out.println("Número: ");
						String numero = sc.nextLine();
						
						System.out.println("Bairro: ");
						String bairro = sc.nextLine();
						
						System.out.println("CEP: ");
						String cep = sc.nextLine();
						
						
						Empresa empr1 = new Empresa(null, nomefantasia, razaoSocial, telefone, email, cnpj, logradouro, numero, bairro, cep); 
						empresaServico.inserirAtualizar(empr1);
		
						break;
				
					case 2: //Alterar Empresa
						System.out.println("Digite o código da Empresa: ");
						cod1 = Integer.parseInt(sc.nextLine());
						
						Empresa empresa2 = empresaServico.buscar(cod1);
						
							if(empresa2 == null){
								System.out.println("Empresa não localizada. Digite outro código.");
							}else{
								
								System.out.println("Digite o novo nome da Empresa: ");
								String novonomeFantasia = sc.nextLine();
								
								System.out.println("Razão social: ");
								String novarazaoSocial = sc.nextLine();
								
								System.out.println("Telefone: ");
								String novotelefone = sc.nextLine();
								
								System.out.println("E-mail: ");
								String novoemail = sc.nextLine();
								
								System.out.println("CNPJ: ");
								String novocnpj = sc.nextLine();
								
								System.out.println("Logradouro: ");
								String novologradouro = sc.nextLine();
								
								System.out.println("Número: ");
								String novonumero = sc.nextLine();
								
								System.out.println("Bairro: ");
								String novobairro = sc.nextLine();
								
								System.out.println("CEP: ");
								String novocep = sc.nextLine();
								
									Empresa novaempresa = new Empresa(cod1, novonomeFantasia, novarazaoSocial, novotelefone, novoemail, novocnpj, novologradouro, novonumero, novobairro, novocep); 
									empresaServico.inserirAtualizar(novaempresa);
									System.out.println("Dados alterados com sucesso!");
								}
							
							break;
					case 3://Pesquisar Empresa
						System.out.println("Digite o código da Empresa:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Empresa empresa3 = empresaServico.buscar(codigo);
						
								if(empresa3 == null){
									System.out.println("Erro: Empresa não localizada. Digite outro valor válido.");
		
								}else{
									empresaServico.buscar(codigo);
									System.out.println("Código da Empresa:" + empresa3.getCodEmpresa());
									System.out.println("Nome fantasia:" + empresa3.getNomeFantasia());
									System.out.println("Razão social:" + empresa3.getRazaoSocial());
									System.out.println("Telefone:" + empresa3.getTelefone());
									System.out.println("E-mail:" + empresa3.getEmail());
									System.out.println("CNPJ:" + empresa3.getCnpj());
									System.out.println("Logradouro:" + empresa3.getLogradouro());
									System.out.println("Número:" + empresa3.getNumero());
									System.out.println("Logradouro:" + empresa3.getBairro());
									System.out.println("Número:" + empresa3.getNumero());
									System.out.println("Bairro:" + empresa3.getBairro());
									System.out.println("CEP:" + empresa3.getCep());
							}
						break;
				
					case 4://Excluir Empresa
						System.out.println("Digite o código da Empresa:");
						codigo = Integer.parseInt(sc.nextLine());
						
						Empresa empresa4 = empresaServico.buscar(codigo);
						
								if(empresa4 == null){
									System.out.println("Erro: Empresa não localizada. Encontre outro valor.");
		
								}else{
									empresaServico.excluir(empresa4);
									System.out.println("Empresa excluída com sucesso!");
							}
							
					case 5://Sair
						System.out.println("Alterações na Empresa finalizadas.\n");
						break;
					
					default:
						System.out.println("Opção inválida!");
					}
				} while(op2 != 5);
				break;

			case 14:
				System.out.println("Programa encerrado.");
				break;
			}

		} while (op != 14);

	}

}
