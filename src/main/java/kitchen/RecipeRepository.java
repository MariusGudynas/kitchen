package kitchen;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
	
	Iterable<Recipe> findByCookId(String cook_id);
}