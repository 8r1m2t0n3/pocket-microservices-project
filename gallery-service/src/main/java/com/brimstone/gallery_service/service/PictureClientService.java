package com.brimstone.gallery_service.service;

import com.brimstone.gallery_service.mapper.PictureMapper;
import com.brimstone.gallery_service.model.dto.PictureDto;
import com.brimstone.gallery_service.model.pojo.Picture;
import com.brimstone.picture_grpc_server.proto.PictureCreationRequest;
import com.brimstone.picture_grpc_server.proto.PictureCreationResponse;
import com.brimstone.picture_grpc_server.proto.PictureRequest;
import com.brimstone.picture_grpc_server.proto.PictureResponse;
import com.brimstone.picture_grpc_server.proto.PictureServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PictureClientService {

  private final PictureServiceGrpc.PictureServiceBlockingStub stub;
  private final PictureMapper pictureMapper;

  public PictureClientService(PictureMapper pictureMapper) {
    ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9090").usePlaintext().build();
    this.stub = PictureServiceGrpc.newBlockingStub(channel);
    this.pictureMapper = pictureMapper;
  }

  public void save(Picture picture) {
    PictureCreationResponse response = stub.save(PictureCreationRequest.newBuilder()
        .setImage(ByteString.copyFrom(picture.getImageData()))
        .setTitle(picture.getTitle())
        .setAuthor(picture.getAuthor())
        .setCreationYear(picture.getCreationYear())
        .build());

    log.info(response.getMessage());
  }

  public PictureDto getById(String id) {
    PictureResponse pictureResponse = stub.getById(PictureRequest.newBuilder().setId(id).build());
    return pictureMapper.toDto(pictureResponse);
  }
}
