package kitchen;

import java.io.IOException;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	@Autowired
	private CookRepository cook_repository;	
	@Autowired
	private RecipeRepository recipe_repository;	
	
	@Autowired 
	EntityManagerFactory factory;	
	
	// @Bean
	public SessionFactory sessionFactory() {

		
	        if (factory.unwrap(SessionFactory.class) == null) {
	            throw new NullPointerException("factory is not a hibernate factory");
	        }
	        return factory.unwrap(SessionFactory.class);
	}	
	
	
	@RequestMapping(path="/recipes", method={ RequestMethod.GET, RequestMethod.POST })
	public String recipes ( Model model,
			@RequestParam(name="cook_id", required=true, defaultValue="-") String cook_id,
			@RequestParam(name="name", required=true, defaultValue="-") String name,
			@RequestParam(name="calories", required=true, defaultValue="-") String calories,
			@RequestParam(name="price", required=true, defaultValue="-") String price,
			@RequestParam(name="send", required=true, defaultValue="-") String send
			) throws IOException{
		String url = "recipes";
		System.out.println(cook_id);
		if(send != null && send.equals("siusti") ) {
			
			Recipe recipe = new Recipe(
					cook_id,
					name,
					calories,
					price
					);
			recipe = recipe_repository.save(recipe);
			
		}
		Iterable<Recipe> recipe_all;
		
		if(cook_id.equals("-")) {
			recipe_all = recipe_repository.findAll();
		}else {
			recipe_all = recipe_repository.findByCookId(cook_id);
		}
		
		model.addAttribute("recipes", recipe_all);
		
		return url;
		
		
	}
	
	@RequestMapping(path="/cooks", method={ RequestMethod.GET, RequestMethod.POST })
	public String cooks( 
			@RequestParam(name="name", required=true, defaultValue="-") String name,
			@RequestParam(name="surname", required=true, defaultValue="-") String surname,
			@RequestParam(name="age", required=true, defaultValue="-") String age,
			@RequestParam(name="began_working", required=true, defaultValue="-") String began_working,
			@RequestParam(name="email", required=true, defaultValue="-") String email,
			@RequestParam(name="phone_number", required=true, defaultValue="-") String phone_number,
			@RequestParam(name="send", required=true, defaultValue="-") String send,
			Model model ) throws IOException {
		
		Iterable<Cook> cook_all = cook_repository.findAll();
		
		model.addAttribute("cooks", cook_all);
		
		if(send != null && send.equals("siusti") ) {
			
			Cook cook = new Cook(
					name,
					surname,
					age,
					began_working,
					email,
					phone_number
					);
			
			if(!cook.checkEmail(email)) {
				System.out.println(email);
				return "cooks";
			}
			
			
			cook = cook_repository.save(cook);
			
			return "redirect:recipes?cook_id=" + cook.getId();
		}
		
		return "cooks";
	}
	
	@RequestMapping(path="/recipeReport", method={ RequestMethod.GET, RequestMethod.POST })
	public String recipeReport ( Model model,
			@RequestParam(name="price_from", required=true, defaultValue="-") String price_from,
			@RequestParam(name="price_to", required=true, defaultValue="-") String price_to,
			@RequestParam(name="send", required=true, defaultValue="-") String send
			) throws IOException{
		String url = "recipeReport";
		Iterable<RecipeReport> recipe_all;
		
		Session session = this.sessionFactory().openSession();
		RecipeReportAtaskaita recipe_report_ataskaita =  new RecipeReportAtaskaita( session );
		
		if(send != null && send.equals("siusti")) {
			recipe_all = recipe_report_ataskaita.recipePriceRange(price_from, price_to);
		}else {
			recipe_all = recipe_report_ataskaita.recipePriceRange(null, null);
		}
		
		model.addAttribute("recipes", recipe_all);
		
		return url;
		
		
	}
}