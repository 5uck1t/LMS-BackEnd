package com.example.demo.repository;

import com.example.demo.model.ProfilePhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilePhotoRepository extends CrudRepository<ProfilePhoto, Long> {

    Optional<ProfilePhoto> findByIme(String ime);
}
