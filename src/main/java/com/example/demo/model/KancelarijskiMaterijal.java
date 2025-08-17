//package com.example.demo.model;
//
//public class KancelarijskiMaterijal {
//    private String id;                  // UUID ili URI
//    private String naziv;
//    private int kolicina;
//    private String opis;
//    private String datumNarudzbine;     // npr. "2025-08-15"
//    private String radnik;              // ime ili ID radnika
//    private String status;              // uToku, odobreno, isporučeno
//
//    public KancelarijskiMaterijal() {}
//
//    public KancelarijskiMaterijal(String id, String naziv, int kolicina, String opis, String datumNarudzbine, String radnik, String status) {
//        this.id = id;
//        this.naziv = naziv;
//        this.kolicina = kolicina;
//        this.opis = opis;
//        this.datumNarudzbine = datumNarudzbine;
//        this.radnik = radnik;
//        this.status = status;
//    }
//
//    // getters i setters za sva polja
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
//    public String getNaziv() { return naziv; }
//    public void setNaziv(String naziv) { this.naziv = naziv; }
//    public int getKolicina() { return kolicina; }
//    public void setKolicina(int kolicina) { this.kolicina = kolicina; }
//    public String getOpis() { return opis; }
//    public void setOpis(String opis) { this.opis = opis; }
//    public String getDatumNarudzbine() { return datumNarudzbine; }
//    public void setDatumNarudzbine(String datumNarudzbine) { this.datumNarudzbine = datumNarudzbine; }
//    public String getRadnik() { return radnik; }
//    public void setRadnik(String radnik) { this.radnik = radnik; }
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//}
package com.example.demo.model;

import java.util.UUID;

public class KancelarijskiMaterijal {
    private String id;                  // UUID
    private String naziv;
    private int kolicina;
    private String opis;
    private String datumNarudzbine;     // npr. "2025-08-15"
    private String radnik;              // ime ili ID radnika
    private String status;              // uToku, odobreno, isporučeno

    public KancelarijskiMaterijal() {
        // automatski generiše ID ako nije postavljen
        this.id = "KM" + UUID.randomUUID().toString().replace("-", "");
    }

    public KancelarijskiMaterijal(String naziv, int kolicina, String opis,
                                  String datumNarudzbine, String radnik, String status) {
        this.id = "KM" + UUID.randomUUID().toString().replace("-", "");
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.opis = opis;
        this.datumNarudzbine = datumNarudzbine;
        this.radnik = radnik;
        this.status = status;
    }

    // getters i setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public String getDatumNarudzbine() { return datumNarudzbine; }
    public void setDatumNarudzbine(String datumNarudzbine) { this.datumNarudzbine = datumNarudzbine; }

    public String getRadnik() { return radnik; }
    public void setRadnik(String radnik) { this.radnik = radnik; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
