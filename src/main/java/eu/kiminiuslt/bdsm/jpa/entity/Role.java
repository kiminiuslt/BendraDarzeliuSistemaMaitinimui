package eu.kiminiuslt.bdsm.jpa.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "bdsm")
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Override
  public String getAuthority() {
    return "ROLE_" + this.name;
  }
}
