package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.files.dto.FileResponse;
import eu.kiminiuslt.bdsm.core.files.service.FilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@Api(tags = "File Management")
@RequiredArgsConstructor
public class FileApiController {
  final String AUTHENTICATION = "Authentication required";
  final String AUTHORIZATION = "User don't have permission use this function";

  private final FilesService filesService;

  @ApiOperation(value = "Save image", httpMethod = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Successfully saved image"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  @PostMapping("upload")
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasRole('DIETIST')")
  public FileResponse saveFile(@RequestParam MultipartFile file) {
    return filesService.saveFile(file);
  }
}
