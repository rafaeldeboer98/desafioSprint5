package br.com.trabalho;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.Validator;

public class Cliente {

	// atributos
	private int cod;
	private String nome;
	private int idade;
	private int resposta = 1;
	private String cpf;
	private String endereco;

	// getters e setters
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void CadastrarCliente() throws InputMismatchException {

		do {
			// pedindo o código para cadastrar

			Scanner sc = new Scanner(System.in);

			try {
				// pedindo o código
				System.out.println("\nDigite o seu código: ");
				cod = Integer.parseInt(sc.nextLine());

				// pedindo o nome
				System.out.println("\nDigite o seu nome: ");
				nome = sc.nextLine();

				// pedindo a idade
				System.out.println("\nDigite a sua idade: ");
				idade = Integer.parseInt(sc.nextLine());
				// ------------------------------------

			} catch (NumberFormatException e) {
				System.out.println("NumberFormatException: " + e.getMessage());
			}

			// pedindo o endereço
			System.out.println("\nDigite o seu endereço: ");
			endereco = sc.nextLine();

			// e por último pegando o CPF e verificando se ele é válido ou inválido
			System.out.println("\nDigite o seu CPF: ");
			cpf = sc.next();

			try {
				validarDocumentos(new CPFValidator(), cpf);
				System.out.println("CPF Válido");
				System.out.println("Cliente cadastrado!!!");

			} catch (InvalidStateException e) {
				System.out.println("CPF Inválido: " + e);
				System.out.println("Verifique se o CPF foi digitado corretamente!!");
			}

			// digitar 1 para voltar ao menu anterior
			System.out.println(" Digite 1 para voltar ao menu anterior!!!");
			resposta = sc.nextInt();

		} while (resposta != 1);

	}

	@Override
	public String toString() {
		return "Cod: " + cod + ", Nome: " + nome + ", Idade: " + idade + ", Endereço: " + endereco + ", CPF: " + cpf;
	}

	public void serializarParaXMLCliente(List<Cliente> listaClientes) {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			// serializar o Objeto numa string XML
			String xmlString = xmlMapper.writeValueAsString(listaClientes);

			// escrever no console
			System.out.println(xmlString);

			// escrevendo o XML string para o arquivo
			File xmlOutput = new File("Cliente.xml");
			FileWriter fileWriter = new FileWriter(xmlOutput, false);
			fileWriter.write(xmlString);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			// handle exception
		} catch (IOException e) {
			// handle exception
		}
	}

	private static void validarDocumentos(Validator<String> validador, String documento) {
		validador.assertValid(documento);
	}

}
