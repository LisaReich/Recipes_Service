package recipes.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Name can not be blank.")
    private String name;

    @NotBlank(message = "Description can not be blank.")
    private String description;

    @NotBlank(message = "Category can not be blank.")
    private String category;

    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @NotEmpty
    @Size(min = 1, message = "Recipe should contain at least one ingredient")
    private String[] ingredients;
    @NotEmpty
    @Size(min = 1, message = "Recipe should contain at least one direction")
    private String[] directions;

    public Recipe(String name, String category, String description, String[] ingredients, String[] directions) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients.clone();
        this.directions = directions.clone();
    }
}
