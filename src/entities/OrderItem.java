package entities;

public class OrderItem {

	private String name;
	private Integer quantity;
	private Product product;

	public OrderItem() {
	}

	public OrderItem(Product product, int quantity) {
		setQuantity(quantity);
		this.product = product;
	}

	public OrderItem(String name, Integer quantity, Product product) {
		this.name = name;
		this.quantity = quantity;
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double subTotal() {
		return product.getPrice() * getQuantity();
	}

}
