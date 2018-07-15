/**
 *
 *  @author Szarek Marcin S15541
 *
 */

package zad1;


public class Purchase {
	
	String id;
	String name;
	String product;
	Double price;
	Double qty;
	
	public Purchase (String id, String name, String product, Double price, Double qty) {
		this.id = id;
		this.name = name;
		this.product = product;
		this.price = price;
		this.qty = qty;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getProduct(){
		return product;
	}
	
	public Double getPrice(){
		return price;
	}
	
	public Double getQty(){
		return qty;
	}
	
	public Double getExpense(){
		return getPrice()*getQty();
	}
	
	public String toString(){
		return this.getId() + ";" + this.getName() + ";" + this.getProduct() + ";" + this.getPrice() + ";" + this.getQty() + ";";
	}
}
