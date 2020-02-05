package br.com.trabalho;

import java.util.Scanner;

public class Estoque extends Produto {

	private int codigo;
	private String nome;
	private int resposta;

	public void VerificarEstoque() {
		do {

			Scanner sc = new Scanner(System.in);
			System.out.println("\nDigite o código do produto: ");
			codigo = sc.nextInt();

			System.out.println(" Digite 1 para voltar ao menu anterior!!!");
			resposta = sc.nextInt();

		} while (resposta != 1);

	}

}
