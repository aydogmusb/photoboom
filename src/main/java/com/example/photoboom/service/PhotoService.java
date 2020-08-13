package com.example.photoboom.service;

import com.example.photoboom.entity.Photo;
import com.example.photoboom.form.PhotoAddForm;
import com.example.photoboom.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void addPhoto(PhotoAddForm form) {
        Photo photo = new Photo(form.getTitle(), form.getTag());
        photoRepository.save(photo);
    }

    public List<Photo> retrievePhotos() {
        List<Photo> photos = photoRepository.findAll();
        if (photos.isEmpty()) {
            throw new NoSuchElementException();
        }
        return photos;
    }

    public Optional<Photo> retrievePhotoById(Long id) {
        Optional<Photo> photo = photoRepository.findById(id);
        return photo;
    }

    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
