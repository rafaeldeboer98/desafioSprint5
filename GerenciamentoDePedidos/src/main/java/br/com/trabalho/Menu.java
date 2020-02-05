package br.com.trabalho;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*Descrição: Desafio (Exercício): Criar um programa de linha de comando, em Java 8, para gerenciamento de pedidos. O programa deve respeitar as seguintes premissas:
 
1) Possuir um menu principal, numerado com as seguintes opções: 1 - Verificar Estoque, 2 - Realizar Pedido, 3 - Cadastrar Produto, 4 - Cadastrar Cliente, 5 - Sair.
2) Os objetos que representam o modelo de dados devem ser expressados em classes Java e seus relacionamentos.
3) Os dados de Estoque, Pedidos, Produtos e Clientes devem ser salvos em um arquivo XML, utilizando coleções de dados Java para percorrer, salvar e resgatar informações. Devendo ser salvos localmente na máquina e no diretório onde está rodando o programa.
4) Para cada classe criada, o programador deve avaliar quais atribuitos serão necessários para sua construção, acrescentando e removendo caso necessário.
5) O programa deve controlar exceções que podem ocorrer durante a excução, e no caso de erro, a exceção deve ser capturada, tratada e uma mensagem de erro amigável deve ser exibida ao usuário.
6) As opções do Menu devem possuir alteranativa de voltar ao menu principal uma vez navegando nos menus internos. (ex: Se o usuário entrou na opção 1 e esta verificando o estoque de produtos, ele deve ter uma opção de voltar ao menu anterior). O mesmo vale para os outros menus.
7) A opção "2 - Realizar Pedido" deve possibilitar o usuário informar dados de pedido: Produto, Cliente sendo o campo quantidade de tipo primitivo/simples.
8) Programa deve consultar o estoque antes de uma venda, e não deve possibilitar a venda de produtos os quais nao constam no estoque.
9) O modelo de dados do Pedido pode ser composto por objetos Cliente, Produto sendo o campo quantidade primitivo.
10) A coleta de dados do usuário deve ser feita pelo programa pelo teclado, capturando o que o usuário digitou como opção e avaliando.
11) O programa deve ser entregue em formato *.jar executável! Devendo criar os arquivos XML de dados caso não existam no mesmo diretório de onde foi executado.
12) Para as opções 3 e 4 do menu, as listagens dos cadastros, devem ser apresentados em ordem alfabética (pelo campo Nome), tanto quando o usuario entra na lista de Produtos quanto de Clientes.*/

public class Menu {

	public static void main(String[] args) throws NoSuchElementException, InputMismatchException, IOException {

		Scanner sc = new Scanner(System.in);

		List<Produto> listaProdutos = new ArrayList<>();
		List<Cliente> listaClientes = new ArrayList<>();
		// tela de menu
		System.out.println("Olá, seja bem-vindo!!!");
		int opcao;

		do {

			System.out.println(
					" 1 - Verificar Estoque\n 2 - Realizar Pedido\n 3 - Cadastrar Produto\n 4 - Cadastrar Cliente\n 5 - Sair");
			System.out.println("____________________________________");
			System.out.println("Digite a sua opção: ");
			opcao = sc.nextInt();

			if (opcao == 1) {

				//vai mostrar os itens cadastrados
				System.out.println(listaProdutos);
				System.out.println("_____________________________________");
				System.out.println("");
			}

			if (opcao == 2) {
				
				//mostrando os clientes
				System.out.println(listaClientes);
				System.out.println("_____________________________________");
				System.out.println("");
			}

			if (opcao == 3) {

				// estanciando o objeto Produto
				Produto produto = new Produto();
				produto.CadastrarProduto();

				// adicionando na lista
				listaProdutos.add(produto);

				// serializando para o formato XML
				System.out.println("Serializando no XML...");
				produto.EscrevendoParaXMLProduto(listaProdutos);
				
				//deserializando para o formato XML
				System.out.println("Deserializando no XML...");
				produto.LendoXML();

				// ordenando na lista por ordem alfabética
				listaProdutos.sort(Comparator.comparing(Produto::getNome));

				// printando na tela a lista ordenada
				System.out.println(listaProdutos);
				System.out.println("_____________________________________");
				System.out.println("");

			}

			if (opcao == 4) {

				//estanciando o objeto Cliente
				Cliente cliente = new Cliente();
				cliente.CadastrarCliente();

				// adicionando na lista
				listaClientes.add(cliente);

				// serializando para o formato XML
				System.out.println("Serializando no XML...");
				cliente.serializarParaXMLCliente(listaClientes);

				// ordenando na lista por ordem alfabética
				listaClientes.sort(Comparator.comparing(Cliente::getNome));

				// printando na tela a lista ordenada
				System.out.println(listaClientes);
				System.out.println("_____________________________________");
				System.out.println("");

			}

			if (opcao == 5) {
				// saindo do programa

				System.out.println("Volte sempre!!!");
				System.exit(0);

			}
			//se for digitada outra opção que não existe
			if(opcao >=6) {
				System.out.println("Opção incorreta, digite apenas de 1 a 5!!");
				System.out.println("Reinicie o programa!!");
				break;
			}

		} while (opcao != 5);

	}

}

//https://stackabuse.com/serialize-and-deserialize-xml-in-java-with-jackson/
