package recipes.presentationlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.RecipeService;
import recipes.businesslayer.Recipe;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/api/recipe")
@Validated
public class RecipesController {

    @Autowired
    RecipeService recipeService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/new")
    public Map<String, Long> addRecipe(@RequestBody @Valid Recipe recipe) {
        return Collections.singletonMap("id", recipeService.save(recipe));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") @Min(1) long id) {
        return recipeService.getRecipeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable("id") @Min(1) long id) {
        recipeService.deleteRecipeById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateRecipe(@RequestBody @Valid Recipe recipe,
                             @PathVariable("id") @Min(1) long id) {
        recipeService.updateRecipe(id, recipe);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search", params = "category")
    public Recipe[] searchByCategory(@RequestParam @Valid String category) {
        return recipeService.findRecipesByCategory(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search", params = "name")
    public Recipe[] searchByName(@RequestParam @Valid String name) {
        return recipeService.findRecipesByName(name);
    }
}
