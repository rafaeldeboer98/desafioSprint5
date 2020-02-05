package br.com.trabalho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Produto {

	// atributos
	protected int cod;
	protected String nome;
	private double preco;
	private int quantidade;

	// getters e setters
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void CadastrarProduto() {
		int resposta;
		do {

			Scanner sc = new Scanner(System.in);
			System.out.println("\nDigite o código do produto: ");
			this.cod = sc.nextInt();

			System.out.println("\nDigite o nome do produto: ");
			this.nome = sc.next();

			System.out.println("\nDigite o preço: ");
			this.preco = sc.nextDouble();

			System.out.println("\nDigite a quantidade: ");
			this.quantidade = sc.nextInt();

			System.out.println("Cadastrado com sucesso!!");

			System.out.println(" Digite 1 para voltar ao menu anterior!!!");
			resposta = sc.nextInt();

		} while (resposta != 1);

	}

	@Override
	public String toString() {
		return "Cod: " + cod + ", Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade;

	}

	public void EscrevendoParaXMLProduto(List<Produto> listaProdutos) {
		try {
			XmlMapper xmlMapper = new XmlMapper();

			// serializar o Objeto numa string XML
			String xmlString = xmlMapper.writeValueAsString(listaProdutos);

			// escrever no console
			System.out.println(xmlString);

			// escrevendo o XML string para o arquivo
			File xmlOutput = new File("Produto.xml");
			FileWriter fileWriter = new FileWriter(xmlOutput, false);
			fileWriter.write(xmlString);
			fileWriter.close();
		} catch (JsonProcessingException e) {
			// handle exception
		} catch (IOException e) {
			// handle exception
		}
	}
	
	//não está funcionando
	public void LendoXML() {
		try {
	        XmlMapper xmlMapper = new XmlMapper();

	        // read file and put contents into the string
	        String readContent = new String(Files.readAllBytes(Paths.get("to_produto.xml")));

	        // deserialize from the XML into a Phone object
	        Produto deserializedData = xmlMapper.readValue(readContent, Produto.class);

	        // Print object details
	        deserializedData.toString();
	        System.out.println("Deserialized data: ");
	        System.out.println("\tCod: " + deserializedData.getCod());
	        System.out.println("\tName: " + deserializedData.getNome());
	        System.out.println("\tPreco: " + deserializedData.getPreco());
	        System.out.println("\tQtd: " + deserializedData.getQuantidade());
	    } catch (IOException e) {
	        // handle the exception
	    }
	
}
	//https://stackify.com/java-xml-jackson/
	
}
