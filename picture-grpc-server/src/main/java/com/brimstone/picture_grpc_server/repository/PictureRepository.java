package com.brimstone.picture_grpc_server.repository;

import com.brimstone.picture_grpc_server.entity.Picture;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends MongoRepository<Picture, String> {
}
