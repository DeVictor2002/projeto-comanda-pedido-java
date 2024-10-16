package application;

import java.util.Scanner;

import entities.Client;
import entities.Order;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Place your order:");
		System.out.println("Client data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		Client client = new Client(name, email);
		Order order = new Order(client);
		System.out.println();
		
		System.out.print("Enter the number of products: ");
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			System.out.println("Product #" + (i + 1) + " data:");
			System.out.print("Product name:");
			sc.nextLine();
			String productName = sc.nextLine();
			System.out.print("Product price: ");
			double productPrice = sc.nextDouble();
			System.out.print("Product quantity: ");
			int productQuantity = sc.nextInt();
			order.addItem(productName, productPrice, productQuantity);
			order.showOrderSummary();
			System.out.println();
		}
		
		
		sc.close();
	}

}
