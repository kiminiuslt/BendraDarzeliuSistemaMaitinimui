package eu.kiminiuslt.bdsm.model;

import eu.kiminiuslt.bdsm.model.enums.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
  private String username;
  private String password;
  private JobPost jobPost;
}
