package com.example.photoboom.controller;

import com.example.photoboom.entity.Photo;
import com.example.photoboom.form.PhotoAddForm;
import com.example.photoboom.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(value = "/home")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/photos")
    public ModelAndView retrievePhotos() {
        try {
            List<Photo> photos = photoService.retrievePhotos();
            return new ModelAndView("photos", "photos", photos);
        } catch (NoSuchElementException e) {
            logger.error("Could not found photo");
        }
        return null;
    }

    @GetMapping(value = "/photo/{id}")
    public ModelAndView retrievePhotoById(@PathVariable Long id) {
        Photo photo = photoService.retrievePhotoById(id);
        return new ModelAndView("photo", "photo", photo);
    }

    @GetMapping("/photos/add")
    public ModelAndView itemAddPage() {
        return new ModelAndView("addPhoto", "photoAddForm", new PhotoAddForm());
    }

    @PostMapping(value = "/photos")
    public String addPhoto(@ModelAttribute("photoAddForm") PhotoAddForm photoAddForm) {
        photoService.addPhoto(photoAddForm);
        return "redirect:/photos";
    }

    @PostMapping(value = "/photo/{id}")
    public String deletePhoto(@PathVariable Long id) {
        photoService.deletePhoto(id);
        return "redirect:/photos";
    }
}
