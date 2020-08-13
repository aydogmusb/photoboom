package com.example.photoboom.service;

import com.example.photoboom.entity.Photo;
import com.example.photoboom.form.PhotoAddForm;
import com.example.photoboom.repository.PhotoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceTest {

    private PhotoService photoService;

    @Mock
    private PhotoRepository photoRepository;

    @Before
    public void setUp() {
        photoService = new PhotoService(photoRepository);
    }

    @Test
    public void should_add_photo() {
        //given
        PhotoAddForm form = new PhotoAddForm();
        form.setTitle("title");
        form.setTag("tag");

        //when
        photoService.addPhoto(form);

        //then
        verify(photoRepository).save(any(Photo.class));
    }

    @Test
    public void should_retrieve_photos() {
        //given
        Photo photo = new Photo();
        photo.setId(1);
        List<Photo> photos = new ArrayList<>();
        photos.add(photo);

        when(photoRepository.findAll()).thenReturn(photos);

        //when
        List<Photo> photoList = photoService.retrievePhotos();

        //then
        assertThat(photoList.size()).isEqualTo(1);
    }

    @Test
    public void should_thow_exception_when_retrieve_photos() {
        //given
        List<Photo> photos = new ArrayList<>();

        when(photoRepository.findAll()).thenReturn(photos);

        //when
        Throwable throwable = catchThrowable(() -> photoService.retrievePhotos());

        //then
        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void should_retrieve_photo_by_id() {
        //given
        when(photoRepository.findById(1L)).thenReturn(Optional.of(new Photo()));

        //when
        Optional<Photo> photo = photoService.retrievePhotoById(1L);

        //then
        assertThat(photo.isPresent()).isEqualTo(true);
    }

    @Test
    public void should_delete_photo_by_id() {
        //when
        photoService.deletePhoto(1L);

        //then
        verify(photoRepository).deleteById(1L);
    }
}