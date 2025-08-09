package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProfilePhoto;
import com.example.demo.repository.ProfilePhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
public class ProfilePhotoController {

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBlob(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();

            Optional<ProfilePhoto> existingPhotoOpt = profilePhotoRepository.findByIme(filename);

            ProfilePhoto photo;
            if (existingPhotoOpt.isPresent()) {
                photo = existingPhotoOpt.get();
                photo.setData(file.getBytes());  // update data
            } else {
                photo = new ProfilePhoto();
                photo.setIme(filename);
                photo.setData(file.getBytes());
            }

            profilePhotoRepository.save(photo);
            return ResponseEntity.ok("Uploaded");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
        }
    }

    @GetMapping("/photo/{ime}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String ime) {
        Optional<ProfilePhoto> photo = profilePhotoRepository.findByIme(ime);
        if(photo.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        byte[] imageData = photo.get().getData();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }
}
