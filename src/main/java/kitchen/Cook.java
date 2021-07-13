package kitchen;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;

@Entity
public class Cook {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String surname;
	private String age;
	private String started_work;
	private String email;
	private String phone_number;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cookId", referencedColumnName="id", insertable=false, updatable=false)    
    private List<Recipe> recipe;     

	
	

	public Cook() {}
	
	public Cook(String name, String surname, String age, String started_work, String email, String phone_number) {
		this.setName(name);
		this.setSurname(surname);
		this.setAge(age);
		this.setStarted_work(started_work);
		this.setEmail(email);
		this.setPhone_number(phone_number);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStarted_work() {
		return started_work;
	}

	public void setStarted_work(String started_work) {
		this.started_work = started_work;
	}
	
	public List<Recipe> getRecipe() {
		return recipe;
	}

	public void setRecipe(List<Recipe> recipe) {
		this.recipe = recipe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean checkEmail(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		 
		return matcher.matches();
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
