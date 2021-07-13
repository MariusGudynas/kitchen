package kitchen;

import javax.persistence.*;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String cookId;
	private String name;
	private String calories;
	private String price;

	@ManyToOne
	@JoinColumn(name="cookId", referencedColumnName="id", insertable=false, updatable=false)
	private Cook cook;
	
	public Recipe() {}
	
	public Recipe(String cook_id, String name, String calories, String price) {
		this.setCookId(cook_id);
		this.setName(name);
		this.setCalories(calories);
		this.setPrice(price);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCookId() {
		return cookId;
	}

	public void setCookId(String cook_id) {
		this.cookId = cook_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Cook getCook() {
		return cook;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}

}
