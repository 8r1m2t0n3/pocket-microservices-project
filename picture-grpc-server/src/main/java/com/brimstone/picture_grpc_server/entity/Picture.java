package com.brimstone.picture_grpc_server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "pictures")
public class Picture {
  @Id
  private String id;
  @Field(name = "image")
  private byte[] imageData;
  private String title;
  private String author;
  private int creationYear;
}
