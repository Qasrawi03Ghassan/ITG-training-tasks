package com.infinite.photos_uploader.Repository;

import org.springframework.data.repository.CrudRepository;
import com.infinite.photos_uploader.Model.Photo;

public interface PhotosRepository extends CrudRepository<Photo, Integer>{}
