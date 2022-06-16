package eu.kiminiuslt.bdsm.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_day", schema = "bdsm", catalog = "postgres")
public class MenuDay {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "day_number")
  private Integer dayNumber;

  @OneToMany
  @JoinTable(schema = "bdsm")
  private Set<Recipe> dayRecipes;
}
