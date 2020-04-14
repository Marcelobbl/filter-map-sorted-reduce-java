package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Produto;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o caminho do arquivo: ");
		String caminho = sc.next();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			
			List<Produto> list= new ArrayList<>();
			
			String linha = br.readLine();
			while (linha != null) {
				String[] campos = linha.split(",");
				list.add(new Produto(campos[0], Double.parseDouble(campos[1])));
				linha = br.readLine();
			}
			
			double media = list.stream().map(p -> p.getPreco()).reduce(0.0, (x,y) -> x + y)/ list.size();
			System.out.println("Media de preco: " + String.format("%.2f", media));
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> nomes = list.stream().filter(p -> p.getPreco() < media).map(p -> p.getNome())
								.sorted(comp.reversed()).collect(Collectors.toList());
			
			nomes.forEach(System.out::println);
			
		}
		catch (IOException e) {
			System.out.println("Erro " + e.getMessage());
		}
		sc.close();

	}

}
