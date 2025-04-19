package com.brimstone.gallery_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PictureDto {
  private String id;
  private byte[] imageData;
  private String title;
  private String author;
  private int creationYear;
}
