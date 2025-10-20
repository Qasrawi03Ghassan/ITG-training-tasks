package com.infinite.photos_uploader.Controller;

import java.io.IOException;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.infinite.photos_uploader.Model.Photo;
import com.infinite.photos_uploader.Service.PhotosService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotosController {

    private final PhotosService photosService;
    public PhotosController(PhotosService photosService){
        this.photosService = photosService;
    }


    @GetMapping("/")
    public String Hello() {
        return "Hello from Spring & Spring Boot.";
    }

    @GetMapping("/api/photos")
    public Iterable<Photo> getPhotos() {
        return photosService.get();
    }
    
    @GetMapping("/api/photos/{id}")
    public Photo getMethodName(@PathVariable Integer id) {
        Photo r = photosService.get(id);
        if(r == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return r;
    }

    @DeleteMapping("/api/photos/{id}")
    public void delete(@PathVariable Integer id) {
        photosService.remove(id);
    }

    @PostMapping("/api/photos")
    public Photo createPhoto(@RequestPart("data") MultipartFile file) throws IOException{
        return photosService.save(file.getOriginalFilename(), file.getContentType(),file.getBytes());
    }
    
    
    
    
}
