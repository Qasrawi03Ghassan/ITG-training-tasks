package com.infinite.photos_uploader.Service;

import org.springframework.stereotype.Service;
import com.infinite.photos_uploader.Model.Photo;
import com.infinite.photos_uploader.Repository.PhotosRepository;

@Service
public class PhotosService {

    private PhotosRepository phRepo;
    public PhotosService(PhotosRepository phRepo) {
        this.phRepo = phRepo;
    }

    public Iterable<Photo> get() {
        return phRepo.findAll();
    }

    public Photo get(Integer id) {
        return phRepo.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        phRepo.deleteById(id);
    }

    public Photo save(String fileName,String contentType,byte[] data) {
        Photo photo = new Photo();
        
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);

        phRepo.save(photo);
        return photo;
    }
    
}
