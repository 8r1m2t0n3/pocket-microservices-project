package com.brimstone.picture_grpc_server.service;

import com.brimstone.picture_grpc_server.entity.Picture;
import com.brimstone.picture_grpc_server.proto.PictureCreationRequest;
import com.brimstone.picture_grpc_server.proto.PictureCreationResponse;
import com.brimstone.picture_grpc_server.proto.PictureRequest;
import com.brimstone.picture_grpc_server.proto.PictureResponse;
import com.brimstone.picture_grpc_server.proto.PictureServiceGrpc;
import com.brimstone.picture_grpc_server.repository.PictureRepository;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class PictureServiceImpl extends PictureServiceGrpc.PictureServiceImplBase {

  private final PictureRepository pictureRepository;

  @Override
  public void save(PictureCreationRequest request, StreamObserver<PictureCreationResponse> responseObserver) {
    Picture savedPicture = pictureRepository.save(
        Picture.builder()
            .imageData(request.getImage().toByteArray())
            .title(request.getTitle())
            .author(request.getAuthor())
            .creationYear(request.getCreationYear())
            .build());

    PictureCreationResponse response = PictureCreationResponse.newBuilder()
        .setId(savedPicture.getId())
        .setMessage("Picture with id: %s saved".formatted(savedPicture.getId()))
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();

    log.info(response.getMessage());
  }

  @Override
  public void getById(PictureRequest request, StreamObserver<PictureResponse> responseObserver) {
    Picture picture = pictureRepository.findById(request.getId())
        .orElseThrow(() -> new RuntimeException("No picture found with id: %s".formatted(request.getId())));

    PictureResponse response = PictureResponse.newBuilder()
        .setId(String.valueOf(picture.getId()))
        .setImage(ByteString.copyFrom(picture.getImageData()))
        .setTitle(picture.getTitle())
        .setAuthor(picture.getAuthor())
        .setCreationYear(picture.getCreationYear())
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
