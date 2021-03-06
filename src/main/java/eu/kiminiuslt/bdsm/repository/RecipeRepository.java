package eu.kiminiuslt.bdsm.repository;

import eu.kiminiuslt.bdsm.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

  Recipe findByUuid(UUID uuid);
}
