package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ProfilePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;

    @Lob
    private byte[] data;

    public ProfilePhoto(Long id, String ime, byte[] data) {
        this.id = id;
        this.ime = ime;
        this.data = data;
    }

    public ProfilePhoto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
