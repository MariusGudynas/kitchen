package kitchen;

public class RecipeReport {
	
	private String name;
	private int calories;
	private double price;

	private String cook_name;
	
	public RecipeReport() {}
	
	public RecipeReport(String name, int calories, double price) {

		this.setName(name);
		this.setCalories(calories);
		this.setPrice(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCook_name() {
		return cook_name;
	}

	public void setCook_name(String cook_name) {
		this.cook_name = cook_name;
	}

}
