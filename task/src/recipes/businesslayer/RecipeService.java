package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import recipes.persistancelayer.RecipeRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    UserService userService;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Long save(Recipe recipe) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());

        recipe.setUser(user);
        recipeRepository.save(recipe);

        return recipe.getId();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public void deleteRecipeById(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());

        if (recipeRepository.findById(id).isPresent()) {

            if (recipeRepository.findById(id).get().getUser().equals(user)) {
                recipeRepository.deleteById(id);
            } else {
                throw new AccessForbiddenException();
            }

        } else {
            throw new NotFoundException();
        }
    }

    public void updateRecipe(Long id, Recipe recipe) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());

        if (recipeRepository.findById(id).isPresent()) {

            if (recipeRepository.findById(id).get().getUser().equals(user)) {
                recipeRepository.findById(id)
                        .map(rec -> {
                            rec.setName(recipe.getName());
                            rec.setCategory(recipe.getCategory());
                            rec.setDate(LocalDateTime.now());
                            rec.setDescription(recipe.getDescription());
                            rec.setDirections(recipe.getDirections());
                            rec.setIngredients(recipe.getIngredients());
                            return recipeRepository.save(rec);
                        });
            } else {
                throw new AccessForbiddenException();
            }

        } else {
            throw new NotFoundException();
        }
    }

    public Recipe[] findRecipesByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public Recipe[] findRecipesByName(String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }
}
