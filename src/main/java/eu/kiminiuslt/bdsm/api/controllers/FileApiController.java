package eu.kiminiuslt.bdsm.api.controllers;

import eu.kiminiuslt.bdsm.core.files.dto.FileResponse;
import eu.kiminiuslt.bdsm.core.files.service.FilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static eu.kiminiuslt.bdsm.commons.Constants.AUTHENTICATION;
import static eu.kiminiuslt.bdsm.commons.Constants.AUTHORIZATION;

@RestController
@RequestMapping("/api/file")
@Api(tags = "File Management")
@RequiredArgsConstructor
public class FileApiController {

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
  @PreAuthorize("hasAnyRole('DIETIST')")
  public FileResponse saveFile(@RequestParam MultipartFile file) {
    return filesService.saveFile(file);
  }

  @ApiOperation(
      value = "Get image by name",
      notes = "File name example: myImage.jpg",
      httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully returned image"),
        @ApiResponse(code = 401, message = AUTHENTICATION),
        @ApiResponse(code = 403, message = AUTHORIZATION)
      })
  @GetMapping("download")
  @PreAuthorize("hasAnyRole('DIETIST','CULINARY')")
  public ResponseEntity<Resource> getFileByFileName(@RequestParam String fileName) {
    return filesService.getResponse(fileName);
  }
}
