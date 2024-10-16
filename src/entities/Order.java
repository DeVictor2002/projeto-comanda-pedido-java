package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private LocalDateTime moment;
	private Client client;
	private List<OrderItem> listOfProducts = new ArrayList<>();

	public Order(Client client) {
		this.moment = LocalDateTime.now();
		this.client = client;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public static DateTimeFormatter getFormatter() {
		return formatter;
	}

	public List<OrderItem> getListOfProducts() {
		return listOfProducts;
	}

	public void addItem(OrderItem item) {
		listOfProducts.add(item);
	}

	public void addItem(Product product, int quantity) {
		listOfProducts.add(new OrderItem(product, quantity));
	}

	public void addItem(String productName, double price, int quantity) {
		Product product = new Product(productName, price);
		listOfProducts.add(new OrderItem(product, quantity));
	}

	public void removeItem(OrderItem item) {
		listOfProducts.remove(item);
	}

	public void removeItem(String productName) {
		listOfProducts.removeIf(item -> item.getProduct().getName().equalsIgnoreCase(productName));
	}

	public void removeItem(Product product) {
		listOfProducts.removeIf(item -> item.getProduct().equals(product));
	}

	public double total() {
		double sum = 0.0;
		for (OrderItem item : listOfProducts) {
			sum += item.subTotal();
		}
		return sum;
	}

	public void showOrderSummary() {

		int maxProductNameLength = "Product".length();
		int maxPriceLength = "Price".length();
		int maxQuantityLength = "Quantity".length();

		for (OrderItem item : listOfProducts) {
			maxProductNameLength = Math.max(maxProductNameLength, item.getProduct().getName().length());
			maxPriceLength = Math.max(maxPriceLength, String.format("%.2f", item.getProduct().getPrice()).length());
			maxQuantityLength = Math.max(maxQuantityLength, String.valueOf(item.getQuantity()).length());
		}

		String separator = String.format("+-%s-+-%s-+-%s-+", "-".repeat(maxProductNameLength),
				"-".repeat(maxPriceLength), "-".repeat(maxQuantityLength));

		System.out.println();
		System.out.println(separator);
		System.out.printf(
				"| %-" + maxProductNameLength + "s | %-" + maxPriceLength + "s | %-" + maxQuantityLength + "s |\n",
				"Product", "Price", "Quantity");
		System.out.println(separator);

		for (OrderItem item : listOfProducts) {
			System.out.printf("| %-" + maxProductNameLength + "s | $%-" + maxPriceLength + ".2f | %-"
					+ maxQuantityLength + "d |\n", item.getProduct().getName(), item.getProduct().getPrice(),
					item.getQuantity());
		}

		System.out.println(separator);
		System.out.printf(
				"| %-" + maxProductNameLength + "s | %-" + maxPriceLength + "s | $%-" + maxQuantityLength + ".2f |\n",
				"Total", "", total());
		System.out.println(separator);
	}
}
