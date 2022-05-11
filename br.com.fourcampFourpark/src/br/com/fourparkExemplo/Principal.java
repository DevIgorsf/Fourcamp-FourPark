package br.com.fourparkExemplo;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Vaga[] vaga = new Vaga[50];
		int opcao;
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 1; i <= 50; i++) {
			vaga[i-1] = new Vaga(i);
		}
		
		Servico servicos = new Servico();
		
		Proprietario p1 = new Proprietario("João da Silva", "999.999.999-99", "(11)1234-5678");
		Veiculos v1 = new Veiculos("XWT7Y89", "Corolla", "carro", p1);
		String horaentrada1 = "13:00";
		servicos.popularVaga(vaga[0], v1, horaentrada1);
		
		while(true) {
			menuPrincipal();
			opcao = scanner.nextInt();
			switch(opcao) {
				case 0:
					System.out.println("\nSistema encerrado");
					break;
				case 1:
					String placa;
					String modelo;
					String marca;
					String nome;
					String documento;
					String telefone;
					String horaentrada;
					Integer posicaoVaga;
					 
					System.out.println("Insira uma placa: ");
					placa = scanner.next();
					
					System.out.println("Insira o modelo do veículo: ");
					modelo = scanner.next();
					
					System.out.println("Insira a  tipo do veículo: ");
					marca = scanner.next();
					
					System.out.println("Insira o nome do proprietario: ");
					nome = scanner.next();
					 
					System.out.println("Insira o documento do proprietario: ");
					documento = scanner.next();
					 
					System.out.println("Insira o telefone do proprietario: ");
					telefone = scanner.next();
					
					System.out.print("Qual a hora de entrada: ");
					horaentrada = scanner.next();
					
					Proprietario proprietario = new Proprietario (nome, documento, telefone);
					Veiculos veiculo = new Veiculos(placa, modelo, marca, proprietario);
					
					while(true) {
						System.out.print("Selecione a vaga:");
						 
						posicaoVaga = scanner.nextInt();
						
						if(servicos.validarVaga(posicaoVaga) && servicos.verificarVagaOcupada(vaga[posicaoVaga-1])) {
							 	
							String retorno = servicos.popularVaga(vaga[posicaoVaga-1], veiculo, horaentrada);
							System.out.println(retorno);
							
							break;
						}
						else {
							 System.out.println("nao possivel utilizar a vaga selecionada;");
						}
					}
					break;
				case 2:
					while(true) {
						System.out.println("Insira uma opçao\n 1- selecionar por vaga\n 2- selecionar por placa\n 0- sair");
						opcao = scanner.nextInt();
						if(opcao == 1) {
							System.out.println("Insira a vaga a desocupar");
							Integer posicao = scanner.nextInt();
							if(servicos.validarVaga(posicao) && !servicos.verificarVagaOcupada(vaga[posicao-1])) {
								System.out.println("Insira o horário de saida");
								String saida = scanner.next();
								System.out.println(servicos.desocuparPorVaga( saida, vaga[posicao-1]));
							} else {
								 System.out.println("nao possivel utilizar a vaga selecionada;");
							}
							break;
						}else if(opcao == 2) {
							System.out.println("Insira a placa que está desocupando");
							String DesocuparPlaca = scanner.next();
							System.out.println("Insira o horário de saida");
							String saida = scanner.next();
							if(servicos.desocuparPorPlaca(DesocuparPlaca, saida, vaga)) {
								break;
							} else {
								System.out.println("Placa não encontrada, tente novamente");
							}
						}else if(opcao == 0) {
							break;
						} else {
							System.out.println("Opção inválida, tente novamente");
							
						}
					}
					break;
				
				case 3:
					String retorno = servicos.listarVagasDisponiveis(vaga);
					System.out.println(retorno);
					break;
					
				case 4:
					String retornoOcupadas = servicos.listarVagasOcupadas(vaga);
					System.out.println(retornoOcupadas);
					break;
						
				case 5:
					System.out.println("R$" + servicos.getFaturamento()); 
					break;
					
				case 6:
					System.out.println("Qual o novo valor/hora do estacionamento? Exemplo: x,xx");
					Double valorHora = scanner.nextDouble();
					servicos.modificaValorHora(valorHora);
					System.out.println("Valor da hora modificado com sucesso"); 
					break;
					
				case 7:
					System.out.println(servicos.exibirLogVeiculo()); 
					break;
						
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
	
	public static void menuPrincipal() {
		System.out.println("\n0 - Sair");
		System.out.println("1 - Registrar entrada");
		System.out.println("2 - Registrar saída");
		System.out.println("3 - Ver vagas disponíveis");
		System.out.println("4 - Ver vagas ocupadas");
		System.out.println("5 - Faturamento");
		System.out.println("6 - Modificar valor/hora");
		System.out.println("7 - Ver registro de veículos que passaram pelo estacionamento");
		System.out.print("Insira uma opção: ");
	}
}