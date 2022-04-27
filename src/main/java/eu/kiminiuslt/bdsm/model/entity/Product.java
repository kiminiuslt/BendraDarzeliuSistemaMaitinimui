package eu.kiminiuslt.bdsm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  @Id
  @GeneratedValue
  private Long id;

  private UUID uuid;

  private String name;

  private Integer kcal;

  private Integer carbs;
}
