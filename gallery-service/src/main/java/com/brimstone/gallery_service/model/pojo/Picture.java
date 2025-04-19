package com.brimstone.gallery_service.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Picture {
  private String id;
  private byte[] imageData;
  private String title;
  private String author;
  private int creationYear;
}
