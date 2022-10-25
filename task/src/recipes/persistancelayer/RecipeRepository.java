package recipes.persistancelayer;

import org.springframework.data.repository.CrudRepository;
import recipes.businesslayer.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Recipe[] findByCategoryIgnoreCaseOrderByDateDesc(String category);
    Recipe[] findByNameIgnoreCaseContainsOrderByDateDesc(String name);
}
