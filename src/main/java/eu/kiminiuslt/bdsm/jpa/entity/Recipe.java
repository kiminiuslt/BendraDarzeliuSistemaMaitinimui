package eu.kiminiuslt.bdsm.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe", schema = "bdsm", catalog = "postgres")
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private UUID uuid;

  private String name;

  @Column(name = "recipe_text")
  private String recipeText;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(schema = "bdsm")
  private Set<ProductAndQuantity> productsList;
}