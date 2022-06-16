package eu.kiminiuslt.bdsm.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history", schema = "bdsm")
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(name = "user_preformed_action")
  private String userPreformedAction;

  @Column(name = "preformed_action")
  private String preformedAction;

  private LocalDateTime timestamp;
}
