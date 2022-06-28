package eu.kiminiuslt.bdsm.core.files.service;

import eu.kiminiuslt.bdsm.core.files.dto.FileResponse;
import eu.kiminiuslt.bdsm.jpa.entity.File;
import eu.kiminiuslt.bdsm.jpa.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
@RequiredArgsConstructor
public class FilesService {
  private final Path fileLocation = Paths.get("./recipesPictures").toAbsolutePath().normalize();
  private final FileRepository fileRepository;

  @Transactional
  public FileResponse saveFile(MultipartFile file) {
    createDirectory();

    try {
      String[] splitFile = file.getOriginalFilename().split("\\.");

      File savedFileInDb =
          fileRepository.save(
              File.builder()
                  .fileName(splitFile[0])
                  .fileExtension(splitFile[1])
                  .size(file.getSize())
                  .mediaType(file.getContentType())
                  .build());

      Path filePathWithFileName = fileLocation.resolve(savedFileInDb.getUniqFileName());
      Files.copy(file.getInputStream(), filePathWithFileName, StandardCopyOption.REPLACE_EXISTING);

      return FileResponse.builder().originalFileName(savedFileInDb.getUniqFileName()).build();
    } catch (IOException e) {
      log.error("Cannot create file", e);
      e.printStackTrace();
    }

    return null;
  }

  private void createDirectory() {
    try {
      if (!Files.exists(fileLocation)) {
        Files.createDirectory(fileLocation);
      }
    } catch (IOException e) {
      log.error("Cannot create directory", e);
      e.printStackTrace();
    }
  }
}
