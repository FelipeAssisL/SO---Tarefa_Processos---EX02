package view;

import java.util.Scanner;

import Controller.KillController;

public class Main {

	public static void main(String args[]) {
		KillController KC = new KillController();
		
		KC.listaProcessos();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o processo que deseja matar: ");
		String process = sc.nextLine();
		sc.close();
		KC.mataPid(process);
	}

}
