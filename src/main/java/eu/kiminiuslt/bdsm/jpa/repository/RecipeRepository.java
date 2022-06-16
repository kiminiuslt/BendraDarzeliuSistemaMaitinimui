package eu.kiminiuslt.bdsm.jpa.repository;

import eu.kiminiuslt.bdsm.jpa.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

  Recipe findByUuid(UUID uuid);
}