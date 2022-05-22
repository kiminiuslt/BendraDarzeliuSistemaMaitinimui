package eu.kiminiuslt.bdsm.recipe.repository;

import eu.kiminiuslt.bdsm.recipe.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

  Recipe findByUuid(UUID uuid);
}
