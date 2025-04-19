package com.brimstone.gallery_service.mapper;

import com.brimstone.gallery_service.model.dto.PictureDto;
import com.brimstone.gallery_service.model.pojo.Picture;
import com.brimstone.picture_grpc_server.proto.PictureResponse;
import com.google.protobuf.ByteString;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PictureMapper {
  PictureDto toDto(Picture picture);

  @Mapping(source = "image", target = "imageData", qualifiedByName = "byteStringToByteArray")
  PictureDto toDto(PictureResponse pictureResponse);

  Picture toEntity(PictureDto pictureDto);

  @Named("byteStringToByteArray")
  default byte[] byteStringToByteArray(ByteString byteString) {
    return byteString.toByteArray();
  }
}
