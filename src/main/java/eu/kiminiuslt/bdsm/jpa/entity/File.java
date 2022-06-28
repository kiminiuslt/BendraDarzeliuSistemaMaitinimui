package eu.kiminiuslt.bdsm.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file", schema = "bdsm")
public class File {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Builder.Default private UUID fileId = UUID.randomUUID();

  @Column(length = 100)
  private String fileName;

  private String fileExtension;

  private String mediaType;

  private long size;

  public String getUniqFileName() {
    return fileName + fileId.toString() + "." + fileExtension;
  }
}
