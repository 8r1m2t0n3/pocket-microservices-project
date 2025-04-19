package com.brimstone.gallery_service.controller;

import com.brimstone.gallery_service.model.dto.PictureDto;
import com.brimstone.gallery_service.model.pojo.Picture;
import com.brimstone.gallery_service.service.PictureClientService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

  private final PictureClientService pictureService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void savePicture(
      @RequestParam("imageData") MultipartFile imageData,
      @RequestParam("title") String title,
      @RequestParam("author") String author,
      @RequestParam("creationYear") Integer creationYear) throws IOException {
    pictureService.save(Picture.builder()
        .imageData(imageData.getBytes())
        .title(title)
        .author(author)
        .creationYear(creationYear)
        .build());
  }

  @GetMapping("/{id}")
  public PictureDto getPictureById(@PathVariable String id) {
    return pictureService.getById(id);
  }
}
