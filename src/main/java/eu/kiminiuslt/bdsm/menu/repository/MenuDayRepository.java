package eu.kiminiuslt.bdsm.menu.repository;

import eu.kiminiuslt.bdsm.menu.model.entity.MenuDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MenuDayRepository extends JpaRepository<MenuDay, Integer> {

  @Transactional
  @Modifying
  @Query(
      value =
          "DELETE FROM bdsm.menu_day_day_recipes u WHERE"
              + " u.menu_day_id =:dayId AND"
              + " u.day_recipes_id = :recipeId",
      nativeQuery = true)
  void removeDayRecipeFromTable(Integer dayId, Integer recipeId);

  @Transactional
  @Modifying
  @Query(
      value =
          "INSERT INTO  bdsm.menu_day_day_recipes (menu_day_id,day_recipes_id) VALUES "
              + " (:dayId ,:recipeId ) ",
      nativeQuery = true)
  void addDayRecipeToTable(Integer dayId, Integer recipeId);
}
